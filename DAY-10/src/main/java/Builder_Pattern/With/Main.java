package Builder_Pattern.With;


import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Scenario 1: Basic Savings Account (Mandatory fields only)
        BankAccount savingsAccount = new BankAccount.Builder(
                "Rahul",
                "SB10001",
                "SAVINGS",
                new BigDecimal("5000"),
                "Hyderabad Branch",
                "HDFC0001234"
        ).build();

        // Scenario 2: Premium Savings Account (All fields enabled)
        BankAccount premiumAccount = new BankAccount.Builder(
                "Amit",
                "SB10002",
                "SAVINGS",
                new BigDecimal("50000"),
                "Bangalore Branch",
                "HDFC0001234"
        )
                .nomineeName("Priya")
                .mobileNumber("9876543210")
                .email("amit@gmail.com")
                .debitCardEnabled(true)
                .internetBankingEnabled(true)
                .build();

        // Scenario 3: Current Account
        BankAccount currentAccount = new BankAccount.Builder(
                "ABC Technologies",
                "CA10001",
                "CURRENT",
                new BigDecimal("100000"),
                "Mumbai Branch",
                "HDFC0005678")
                .mobileNumber("9876543210")
                .email("accounts@abc.com")
                .internetBankingEnabled(true)
                .build();

        // Validation Check Example
        try {
            new BankAccount.Builder(
                    "Invalid User",
                    "SB99999",
                    "FIXED_DEPOSIT", // Invalid account type
                    new BigDecimal("-100"), // Invalid deposit
                    "Delhi Branch",
                    "BAD_IFSC"
            ).build();
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation caught expected error: " + ex.getMessage());
        }

        System.out.println(savingsAccount);

    }
}
