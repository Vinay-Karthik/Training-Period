package service;

import model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountService {

    private final List<Account> accounts = new ArrayList<>();

    // Generate a unique account number
    private String generateUniqueAccountNumber() {

        while (true) {

            String candidate =
                    AccountNumberGenerator.generateAccountNumber();

            boolean exists = accounts.stream()
                    .anyMatch(account ->
                            account.getAccountDetails()
                                    .accountNumber()
                                    .equals(candidate));

            if (!exists) {
                return candidate;
            }
        }
    }

    // Create Savings Account
    public SavingsAccount createSavingsAccount(
            String customerName,
            BigDecimal openingBalance,
            BigDecimal interestRate,
            BigDecimal minimumBalance
    ) {

        String accountNumber = generateUniqueAccountNumber();

        BankAccount bankAccount = new BankAccount(
                accountNumber,
                customerName,
                openingBalance
        );

        SavingsAccount savingsAccount = new SavingsAccount(
                bankAccount,
                interestRate,
                minimumBalance
        );

        accounts.add(savingsAccount);

        return savingsAccount;
    }

    // Create Current Account
    public CurrentAccount createCurrentAccount(
            String customerName,
            BigDecimal openingBalance,
            BigDecimal overdraftLimit
    ) {

        String accountNumber = generateUniqueAccountNumber();

        BankAccount bankAccount = new BankAccount(
                accountNumber,
                customerName,
                openingBalance
        );

        CurrentAccount currentAccount = new CurrentAccount(
                bankAccount,
                overdraftLimit
        );

        accounts.add(currentAccount);

        return currentAccount;
    }

    // Get Account Type Description using Switch Expression
    public String getAccountTypeDescription(Account account) {

        Objects.requireNonNull(account, "Account cannot be null");

        return switch (account.getAccountType()) {

            case SAVINGS -> "Savings Account";

            case CURRENT -> "Current Account";
        };
    }

    // Display Single Account using Pattern Matching
    public void displayAccount(Account account) {

        Objects.requireNonNull(account, "Account cannot be null");

        System.out.println("Account Category: "
                + getAccountTypeDescription(account));

        if (account instanceof SavingsAccount savingsAccount) {

            savingsAccount.displayAccountInformation();

        } else if (account instanceof CurrentAccount currentAccount) {

            currentAccount.displayAccountInformation();
        }
    }

    // Display All Accounts
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No accounts available.");

            return;
        }

        for (Account account : accounts) {

            System.out.println("----------------------------------------");

            displayAccount(account);
        }

        System.out.println("----------------------------------------");
    }

    // Search Account by Account Number
    public Optional<Account> searchAccount(String accountNumber) {

        return accounts.stream()
                .filter(account ->
                        account.getAccountDetails()
                                .accountNumber()
                                .equals(accountNumber))
                .findFirst();
    }

    // Total Account Count
    public long getTotalAccountCount() {

        return accounts.size();
    }

    // Savings Account Count
    public long getSavingsAccountCount() {

        return accounts.stream()
                .filter(account ->
                        account.getAccountType() == AccountType.SAVINGS)
                .count();
    }

    // Current Account Count
    public long getCurrentAccountCount() {

        return accounts.stream()
                .filter(account ->
                        account.getAccountType() == AccountType.CURRENT)
                .count();
    }

    // Calculate Total Opening Balance
    public BigDecimal getTotalOpeningBalance() {

        return accounts.stream()
                .map(account ->
                        account.getAccountDetails().openingBalance())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}