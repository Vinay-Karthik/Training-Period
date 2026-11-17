package Builder_Pattern.Without;


import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Scenario 1: Basic Savings Account (Mandatory fields only)
        BankAccount savingsAccount = new BankAccount(
                "Rahul",
                "SB10001",
                "SAVINGS",
                new BigDecimal("5000"),
                "Hyderabad Branch",
                "HDFC0001234",
                "9876543210", "accounts@abc.com", true);

        // Scenario 2: Premium Savings Account (All fields enabled)
        BankAccount premiumAccount = new BankAccount(
                "Amit",
                "SB10002",
                "SAVINGS",
                new BigDecimal("50000"),
                "Bangalore Branch",
                "HDFC0001234",
                "Priya",
                "9876543210",
                "amit@gmail.com",
                true,
                true
        );


        System.out.println(savingsAccount);
        System.out.println(premiumAccount);
    }
}
