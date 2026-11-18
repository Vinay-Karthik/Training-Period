package Facade_Pattern.With.model;


public class Account {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private boolean active;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this(accountNumber, accountHolderName, balance, true);
    }

    public Account(String accountNumber, String accountHolderName, double balance, boolean active) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.active = active;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
