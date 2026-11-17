package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;

public class BankAccount implements BankOperations {
    protected String accountNumber;
    protected String holderName;
    protected BigDecimal balance;

    public BankAccount(String accountNumber, String holderName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    @Override
    public void deposit(BigDecimal amount, String account_No) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount, String account_No) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        balance = balance.subtract(amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}