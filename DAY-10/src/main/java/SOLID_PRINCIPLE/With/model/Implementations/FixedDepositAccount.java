package SOLID_PRINCIPLE.With.model.Implementations;

import SOLID_PRINCIPLE.With.model.Depositable;

import java.math.BigDecimal;

// LSP fix: FD does NOT extend BankAccount and does NOT implement Withdrawable.
// Money can only leave an FD at maturity via closeForMaturity().
public class FixedDepositAccount implements Depositable {
    private final String accountNumber;
    private final String holderName;
    private final int tenureMonths;
    private BigDecimal balance;
    private boolean matured = false;

    public FixedDepositAccount(String accountNumber, String holderName, BigDecimal balance, int tenureMonths) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.tenureMonths = tenureMonths;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (matured) {
            throw new IllegalStateException("FD account is closed");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance = balance.add(amount);
    }

    public BigDecimal closeForMaturity() {
        if (matured) {
            throw new IllegalStateException("FD already closed");
        }
        matured = true;
        return balance;
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

    public int getTenureMonths() {
        return tenureMonths;
    }
}
