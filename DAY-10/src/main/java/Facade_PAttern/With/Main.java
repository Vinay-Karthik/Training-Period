package Facade_Pattern.With;


import Facade_Pattern.With.facade.BankingFacade;
import Facade_Pattern.With.model.Account;
import Facade_Pattern.With.service.*;

public class Main {

    public static void main(String[] args) {

        // Create bank accounts
        Account sender = new Account("ACC1001", "Rahul", 10000);
        Account receiver = new Account("ACC2001", "Amit", 5000);

        // Initialize subsystems
        AccountService accountService = new AccountService();
        BalanceService balanceService = new BalanceService();
        TransactionService transactionService = new TransactionService();
        NotificationService notificationService = new NotificationService();
        ReceiptService receiptService = new ReceiptService();

        // Register accounts in AccountService
        accountService.registerAccount(sender);
        accountService.registerAccount(receiver);

        // Create facade
        BankingFacade bankingFacade = new BankingFacade(
                accountService,
                balanceService,
                transactionService,
                notificationService,
                receiptService
        );

        // 1. Successful Transfer (Matches Expected Output)
        bankingFacade.transferMoney(sender, receiver, 3000);

        // --- Negative Test Scenarios ---
        System.out.println("\n\n>>> RUNNING NEGATIVE TEST SCENARIOS <<<");

        // 2. Insufficient Balance
        bankingFacade.transferMoney(sender, receiver, 50000);

        // 3. Zero & Negative Transfer Amount
        bankingFacade.transferMoney(sender, receiver, 0);
        bankingFacade.transferMoney(sender, receiver, -500);

        // 4. Invalid Account Number
        bankingFacade.transferMoney("ACC9999", "ACC2001", 1000);

        // 5. Inactive Account
        Account inactiveAcc = new Account("ACC3001", "Vikram", 8000, false);
        accountService.registerAccount(inactiveAcc);
        bankingFacade.transferMoney(inactiveAcc, receiver, 1000);

        // 6. Unexpected Credit Failure & Rollback
        Account faultyReceiver = new Account("ACC_FAIL", "FaultyMerchant", 1000, true);
        accountService.registerAccount(faultyReceiver);
        System.out.println("\nSender balance before failed credit test: Rs. " + (long) sender.getBalance());
        bankingFacade.transferMoney(sender, faultyReceiver, 1000);
        System.out.println("Sender balance after rollback: Rs. " + (long) sender.getBalance());
    }
}
