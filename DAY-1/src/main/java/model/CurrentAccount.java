package model;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Objects;

public final class CurrentAccount implements Account {

    private final BankAccount bankAccount;

    private final BigDecimal overdraftLimit;

    public CurrentAccount(
            BankAccount bankAccount,
            BigDecimal overdraftLimit
    ) {

        this.bankAccount = Objects.requireNonNull(bankAccount);

        this.overdraftLimit = Objects.requireNonNull(overdraftLimit);

        if (overdraftLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Overdraft limit cannot be negative"
            );
        }
    }

    @Override
    public BankAccount getAccountDetails() {
        return bankAccount;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CURRENT;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public String getAccountInformation() {

        return """
                Account Number  : %s
                Customer Name   : %s
                Account Type    : %s
                Opening Balance : %s
                Overdraft Limit : %s
                """.formatted(
                bankAccount.accountNumber(),
                bankAccount.customerName(),
                getAccountType(),
                bankAccount.openingBalance(),
                overdraftLimit
        );
    }

    @Override
    public void displayAccountInformation() {
        System.out.println(getAccountInformation());
    }
}