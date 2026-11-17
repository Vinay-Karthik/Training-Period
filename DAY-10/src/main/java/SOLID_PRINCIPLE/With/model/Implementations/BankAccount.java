package SOLID_PRINCIPLE.With.model.Implementations;

import SOLID_PRINCIPLE.With.model.Depositable;
import SOLID_PRINCIPLE.With.model.Withdrawable;

import java.math.BigDecimal;

// LSP: only accounts that allow withdrawals implement this.
// FixedDepositAccount does NOT implement Withdrawable, so it can never
// be used where a withdrawable account is expected.
public class BankAccount implements Depositable, Withdrawable {
    protected final String accountNumber;
    protected final String holderName;
    protected BigDecimal balance;

    public BankAccount(String accountNumber, String holderName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
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
