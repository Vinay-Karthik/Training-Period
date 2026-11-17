package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        bankService.createAccount("SAVINGS", "SAV-101", "Alice", new BigDecimal("10000.00"));
        bankService.deposit("SAV-101", new BigDecimal("2500.00"));
        System.out.println(bankService.getBalance("SAV-101"));
        bankService.processPayment("UPI", new BigDecimal("500.00"), "SAV-101");
        System.out.println(bankService.getBalance("SAV-101"));

//        // Demonstrating LSP violation crash:
//        bankService.createAccount("FD", "FD-202", "Bob", new BigDecimal("50000.00"));
//        bankService.withdraw("FD-202", new BigDecimal("1000.00"));

        bankService.createAccount("SALARY", "SALARY-202", "Bob", new BigDecimal("25000.00"));
        bankService.withdraw("SALARY-202", new BigDecimal("1000.00"));
        System.out.println(bankService.getBalance("SALARY-202"));


        bankService.transfer(new BigDecimal("1000.00"), "SALARY-202", "SAV-101");
        System.out.println("Transfer completed successfully");
        System.out.println(bankService.getBalance("SALARY-202"));
        System.out.println(bankService.getBalance("SAV-101"));
    }
}