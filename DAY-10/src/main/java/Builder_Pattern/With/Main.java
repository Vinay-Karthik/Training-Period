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

//        BankAccount bankAccount2 = new BankAccount.Builder().email().

        System.out.println(savingsAccount);

    }
}
