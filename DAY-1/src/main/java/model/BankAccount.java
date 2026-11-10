package model;

import java.math.BigDecimal;

public record BankAccount(String accountNumber, String customerName, BigDecimal openingBalance) {

    public BankAccount {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be blank");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }

        // we can use BigDecimal to store big numbers , if we use int instead we cant use null checking ,
        // if we use Bigdecimal we should use compareTo to check negaive value
        if (openingBalance == null) {
            throw new IllegalArgumentException("Opening balance cannot be null");
        }

        if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
    }
}
