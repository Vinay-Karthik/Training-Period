package Facade_Pattern.facade;


import Facade_Pattern.model.Account;
import Facade_Pattern.model.Transaction;
import Facade_Pattern.service.*;

public class BankingFacade {
    private final AccountService accountService;
    private final BalanceService balanceService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;
    private final ReceiptService receiptService;

    public BankingFacade(
            AccountService accountService,
            BalanceService balanceService,
            TransactionService transactionService,
            NotificationService notificationService,
            ReceiptService receiptService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
        this.receiptService = receiptService;
    }

    // Overloaded method supporting account numbers (Section 1)
    public void transferMoney(String senderAccountNumber, String receiverAccountNumber, double amount) {
        try {
            Account sender = accountService.getAccountDetails(senderAccountNumber);
            Account receiver = accountService.getAccountDetails(receiverAccountNumber);
            transferMoney(sender, receiver, amount);
        } catch (Exception e) {
            System.out.println("\n===== BANK TRANSFER STARTED =====\n");
            System.out.println("Transfer rejected: " + e.getMessage());
            notificationService.sendFailureNotification(null, amount, e.getMessage());
            System.out.println("\n===== TRANSFER FAILED =====");
        }
    }

    // Primary method supporting Account objects (Section 4)
    public void transferMoney(Account sender, Account receiver, double amount) {
        System.out.println("\n===== BANK TRANSFER STARTED =====\n");
        boolean debited = false;

        try {
            // 1. Validate transfer amount
            balanceService.validateAmount(amount);

            // 2. Validate sender account
            System.out.println("Validating sender account...");
            Account validSender = accountService.validateAccount(sender);
            System.out.println("Sender account verified.\n");

            // 3. Validate receiver account
            System.out.println("Validating receiver account...");
            Account validReceiver = accountService.validateAccount(receiver);
            System.out.println("Receiver account verified.\n");

            if (validSender.getAccountNumber().equals(validReceiver.getAccountNumber())) {
                throw new IllegalArgumentException("Sender and receiver accounts cannot be the same.");
            }

            // 4. Check available balance
            System.out.println("Checking available balance...");
            balanceService.hasSufficientBalance(validSender, amount);
            System.out.println("Sufficient balance available.\n");

            // 5. Debit sender & Credit receiver with Rollback safety
            balanceService.debit(validSender, amount);
            debited = true;

            balanceService.credit(validReceiver, amount);
            System.out.println();

            // 6. Generate transaction ID and record transaction
            String txnId = transactionService.generateTransactionId();
            Transaction transaction = transactionService.recordTransaction(
                    txnId, validSender, validReceiver, amount, "SUCCESS"
            );
            System.out.println();

            // 7. Send success notifications
            notificationService.sendSuccessNotification(validSender, validReceiver, amount, txnId);

            // 8. Generate and display receipt
            receiptService.generateReceipt(transaction);

            System.out.println("\n===== TRANSFER COMPLETED =====");

        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());

            // Basic rollback if debit succeeded before credit failed
            if (debited) {
                System.out.println("Initiating rollback: Reversing Rs. " + (long) amount + " debit to " + sender.getAccountNumber() + "...");
                sender.setBalance(sender.getBalance() + amount);
                System.out.println("Rollback completed. Restored balance: Rs. " + (long) sender.getBalance());
            }

            if (sender != null && receiver != null) {
                String failedTxnId = transactionService.generateTransactionId();
                Transaction failedTxn = transactionService.recordTransaction(
                        failedTxnId, sender, receiver, amount, "FAILED"
                );
                notificationService.sendFailureNotification(sender, amount, e.getMessage());
                receiptService.generateReceipt(failedTxn);
            } else {
                notificationService.sendFailureNotification(sender, amount, e.getMessage());
            }

            System.out.println("\n===== TRANSFER FAILED =====");
        }
    }
}
