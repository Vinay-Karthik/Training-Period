package SOLID_PRINCIPLE.With.service;

import SOLID_PRINCIPLE.With.model.Implementations.BankAccount;
import SOLID_PRINCIPLE.With.notification.NotificationSender;
import SOLID_PRINCIPLE.With.payment.PaymentProcessor;
import SOLID_PRINCIPLE.With.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SRP: orchestrates account operations only.
// Depends on abstractions (DIP) injected via constructor.
public class BankService {

    private final AccountRepository repository;
    private final AccountService accountService;
    private final NotificationSender notificationSender;
    private final Map<String, PaymentProcessor> paymentProcessors;

    public BankService(AccountRepository repository,
                       AccountService accountService,
                       NotificationSender notificationSender,
                       List<PaymentProcessor> paymentProcessors) {
        this.repository = repository;
        this.accountService = accountService;
        this.notificationSender = notificationSender;
        this.paymentProcessors = new HashMap<>();
        for (PaymentProcessor p : paymentProcessors) {
            this.paymentProcessors.put(p.getPaymentType(), p);
        }
    }

    public void createAccount(String type, String accNo, String name, BigDecimal initialDeposit) {
        BankAccount account = accountService.create(type, accNo, name, initialDeposit);
        repository.save(account);
        notificationSender.send(name, "Account created: " + accNo);
    }

    public void deposit(String accNo, BigDecimal amount) {
        BankAccount account = getAccount(accNo);
        account.deposit(amount);
        notificationSender.send(account.getHolderName(), "Deposited: " + amount);
    }

    public void withdraw(String accNo, BigDecimal amount) {
        BankAccount account = getAccount(accNo);
        account.withdraw(amount);
        notificationSender.send(account.getHolderName(), "Withdrawn: " + amount);
    }

    // OCP: new payment types work automatically once registered — no if/else chain
    public void processPayment(String paymentType, BigDecimal amount, String accNo) {
        PaymentProcessor processor = paymentProcessors.get(paymentType.toUpperCase());
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
        processor.process(amount);
        withdraw(accNo, amount);
    }

    public void transfer(BigDecimal amount, String sourceAccount, String targetAccount) {
        withdraw(sourceAccount, amount);
        deposit(targetAccount, amount);
        System.out.println("Transferred " + amount + " to " + targetAccount);
    }

    public BigDecimal getBalance(String accNo) {
        return getAccount(accNo).getBalance();
    }

    private BankAccount getAccount(String accNo) {
        return repository.findByAccountNumber(accNo)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accNo));
    }
}
