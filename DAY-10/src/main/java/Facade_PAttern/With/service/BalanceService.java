package Facade_Pattern.With.service;


import Facade_Pattern.With.model.Account;

public class BalanceService {

    public double getBalance(Account account) {
        return account.getBalance();
    }

    public void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid transfer amount: Rs. " + (long) amount + ". Amount must be greater than zero.");
        }
    }

    public void hasSufficientBalance(Account account, double amount) {
        validateAmount(amount);
        if (account.getBalance() < amount) {
            throw new IllegalStateException(
                    "Insufficient balance in " + account.getAccountNumber() +
                            ". Available: Rs. " + (long) account.getBalance() +
                            ", Required: Rs. " + (long) amount
            );
        }
    }

    public void debit(Account account, double amount) {
        hasSufficientBalance(account, amount);
        System.out.println("Debiting Rs. " + (long) amount + " from " + account.getAccountNumber() + "...");
        account.setBalance(account.getBalance() - amount);
    }

    public void credit(Account account, double amount) {
        validateAmount(amount);
        // Hook to simulate unexpected credit failure for testing rollback
        if ("ACC_FAIL".equals(account.getAccountNumber())) {
            throw new RuntimeException("Simulated downstream gateway failure while crediting " + account.getAccountNumber());
        }
        System.out.println("Crediting Rs. " + (long) amount + " to " + account.getAccountNumber() + "...");
        account.setBalance(account.getBalance() + amount);
    }
}
