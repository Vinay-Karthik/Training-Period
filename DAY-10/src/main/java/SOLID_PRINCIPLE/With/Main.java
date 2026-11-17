package SOLID_PRINCIPLE.With;

import SOLID_PRINCIPLE.With.notification.Implementations.EmailNotification;
import SOLID_PRINCIPLE.With.payment.Implementations.CardPaymentProcessor;
import SOLID_PRINCIPLE.With.payment.Implementations.GiftCardPayment;
import SOLID_PRINCIPLE.With.payment.Implementations.NetBankingPaymentProcessor;
import SOLID_PRINCIPLE.With.payment.Implementations.UpiPaymentProcessor;
import SOLID_PRINCIPLE.With.repository.Implementations.InMemoryAccountRepository;
import SOLID_PRINCIPLE.With.service.AccountService;
import SOLID_PRINCIPLE.With.service.BankService;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Dependency injection: wire abstractions to implementations
        BankService bankService = new BankService(
                new InMemoryAccountRepository(),
                new AccountService(),
                new EmailNotification(),
                List.of(new UpiPaymentProcessor(), new CardPaymentProcessor(), new NetBankingPaymentProcessor(), new GiftCardPayment())
        );

        bankService.createAccount("SAVINGS", "SAV-101", "Alice", new BigDecimal("10000.00"));
        bankService.deposit("SAV-101", new BigDecimal("2500.00"));
        System.out.println(bankService.getBalance("SAV-101"));
        bankService.processPayment("UPI", new BigDecimal("500.00"), "SAV-101");
        System.out.println(bankService.getBalance("SAV-101"));

        bankService.createAccount("SALARY", "SALARY-202", "Bob", new BigDecimal("25000.00"));
        bankService.withdraw("SALARY-202", new BigDecimal("1000.00"));
        System.out.println(bankService.getBalance("SALARY-202"));

        bankService.transfer(new BigDecimal("1000.00"), "SALARY-202", "SAV-101");
        System.out.println("Transfer completed successfully");
        System.out.println(bankService.getBalance("SALARY-202"));
        System.out.println(bankService.getBalance("SAV-101"));
    }
}
