package Facade_Pattern.Without;

import Facade_Pattern.Without.model.Account;
import Facade_Pattern.Without.model.Transaction;
import Facade_Pattern.Without.service.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bank Transfer (Without Facade Pattern) ===");
        System.out.println("Client must directly coordinate AccountService, BalanceService, TransactionService, NotificationService, and ReceiptService.\n");

        Account sender = new Account("ACC1001", "Rahul", 10000);
        Account receiver = new Account("ACC2001", "Amit", 5000);

        AccountService accountService = new AccountService();
        accountService.registerAccount(sender);
        accountService.registerAccount(receiver);

        BalanceService balanceService = new BalanceService();
        TransactionService transactionService = new TransactionService();
        NotificationService notificationService = new NotificationService();
        ReceiptService receiptService = new ReceiptService();

        double amount = 3000.0;

        try {
            System.out.println("1. Validating amount...");
            balanceService.validateAmount(amount);

            System.out.println("2. Checking sender balance...");
            balanceService.hasSufficientBalance(sender, amount);

            System.out.println("3. Debiting sender & crediting receiver...");
            balanceService.debit(sender, amount);
            balanceService.credit(receiver, amount);

            System.out.println("4. Recording transaction...");
            String txnId = transactionService.generateTransactionId();
            Transaction txn = transactionService.recordTransaction(txnId, sender, receiver, amount, "SUCCESS");

            System.out.println("5. Sending notifications...");
            notificationService.sendSuccessNotification(sender, receiver, amount, txnId);

            System.out.println("6. Generating receipt...");
            receiptService.generateReceipt(txn);

            System.out.println("\nTransfer completed successfully without facade.");

        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
}
