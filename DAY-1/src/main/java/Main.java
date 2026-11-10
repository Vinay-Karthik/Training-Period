

import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import service.AccountService;
import util.InputHandler;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        AccountService accountService = new AccountService();

        InputHandler input = new InputHandler();

        boolean running = true;

        System.out.println("========================================");
        System.out.println("       BANKCORE ACCOUNT SYSTEM");

        try {

            while (running) {

                displayMenu();

                int choice = input.readInt("Enter your choice: ");

                switch (choice) {

                    case 1 -> createSavingsAccount(
                            accountService, input
                    );

                    case 2 -> createCurrentAccount(
                            accountService, input
                    );

                    case 3 -> accountService.displayAllAccounts();

                    case 4 -> searchAccount(
                            accountService, input
                    );

                    case 5 -> displayAccountSummary(
                            accountService
                    );

                    case 6 -> {
                        System.out.println(
                                "\nThank you for using BankCore!"
                        );

                        running = false;
                    }

                    default -> System.out.println(
                            "Invalid choice! Please select 1-6."
                    );
                }
            }

        } finally {
            input.close();
        }
    }

    // Display Main Menu
    private static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("               MAIN MENU");
        System.out.println("========================================");

        System.out.println("1. Create Savings Account");
        System.out.println("2. Create Current Account");
        System.out.println("3. Display All Accounts");
        System.out.println("4. Search Account");
        System.out.println("5. Account Summary");
        System.out.println("6. Exit");

        System.out.println("========================================");
    }

    // Create Savings Account
    private static void createSavingsAccount(
            AccountService accountService,
            InputHandler input
    ) {

        System.out.println("\n========== CREATE SAVINGS ACCOUNT ==========");

        String customerName = input.readString(
                "Enter Customer Name: "
        );

        BigDecimal openingBalance = input.readBigDecimal(
                "Enter Opening Balance: "
        );

        BigDecimal interestRate = input.readBigDecimal(
                "Enter Interest Rate (%): "
        );

        BigDecimal minimumBalance = input.readBigDecimal(
                "Enter Minimum Balance: "
        );

        try {

            SavingsAccount account =
                    accountService.createSavingsAccount(
                            customerName,
                            openingBalance,
                            interestRate,
                            minimumBalance
                    );

            System.out.println("\nSavings Account Created Successfully!");

            System.out.println(
                    "Generated Account Number: "
                            + account.getAccountDetails().accountNumber()
            );

        } catch (IllegalArgumentException exception) {

            System.out.println("Error: " + exception.getMessage());
        }
    }

    // Create Current Account
    private static void createCurrentAccount(
            AccountService accountService,
            InputHandler input
    ) {

        System.out.println("\n========== CREATE CURRENT ACCOUNT ==========");

        String customerName = input.readString(
                "Enter Customer Name: "
        );

        BigDecimal openingBalance = input.readBigDecimal(
                "Enter Opening Balance: "
        );

        BigDecimal overdraftLimit = input.readBigDecimal(
                "Enter Overdraft Limit: "
        );

        try {

            CurrentAccount account =
                    accountService.createCurrentAccount(
                            customerName,
                            openingBalance,
                            overdraftLimit
                    );

            System.out.println("\nCurrent Account Created Successfully!");

            System.out.println(
                    "Generated Account Number: "
                            + account.getAccountDetails().accountNumber()
            );

        } catch (IllegalArgumentException exception) {

            System.out.println("Error: " + exception.getMessage());
        }
    }

    // Search Account
    private static void searchAccount(
            AccountService accountService,
            InputHandler input
    ) {

        System.out.println("\n========== SEARCH ACCOUNT ==========");

        String accountNumber = input.readString(
                "Enter Account Number: "
        );

        Optional<Account> account =
                accountService.searchAccount(accountNumber);

        if (account.isPresent()) {

            System.out.println("\nAccount Found!");

            accountService.displayAccount(account.get());

        } else {

            System.out.println("\nAccount not found!");
        }
    }

    // Display Account Summary
    private static void displayAccountSummary(
            AccountService accountService
    ) {

        System.out.println("\n========== ACCOUNT SUMMARY ==========");

        System.out.println(
                "Total Accounts   : "
                        + accountService.getTotalAccountCount()
        );

        System.out.println(
                "Savings Accounts : "
                        + accountService.getSavingsAccountCount()
        );

        System.out.println(
                "Current Accounts : "
                        + accountService.getCurrentAccountCount()
        );

        System.out.println(
                "Total Opening Balance: "
                        + accountService.getTotalOpeningBalance()
        );

        System.out.println("=====================================");
    }
}