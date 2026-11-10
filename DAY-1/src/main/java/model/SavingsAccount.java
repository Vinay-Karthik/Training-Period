package model;

import java.math.BigDecimal;
import java.util.Objects;

public final class SavingsAccount implements Account {

    private final BankAccount bankAccount;

    private final BigDecimal interestRate;

    private final BigDecimal minimumBalance;

    public SavingsAccount(
            BankAccount bankAccount,
            BigDecimal interestRate,
            BigDecimal minimumBalance
    ) {

        this.bankAccount = Objects.requireNonNull(bankAccount);

        this.interestRate = Objects.requireNonNull(interestRate);

        this.minimumBalance = Objects.requireNonNull(minimumBalance);

        if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be negative"
            );
        }

        if (minimumBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Minimum balance cannot be negative"
            );
        }
    }

    @Override
    public BankAccount getAccountDetails() {
        return bankAccount;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public String getAccountInformation() {

        return """
                Account Number  : %s
                Customer Name   : %s
                Account Type    : %s
                Opening Balance : %s
                Interest Rate   : %s%%
                Minimum Balance : %s
                """.formatted(
                bankAccount.accountNumber(),
                bankAccount.customerName(),
                getAccountType(),
                bankAccount.openingBalance(),
                interestRate,
                minimumBalance
        );
    }

    @Override
    public void displayAccountInformation() {
        System.out.println(getAccountInformation());
    }
}