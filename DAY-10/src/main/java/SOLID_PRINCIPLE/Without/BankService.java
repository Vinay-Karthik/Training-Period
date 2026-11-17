package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankService {

    private final EmailNotification emailNotification = new EmailNotification();
    private final Map<String, BankAccount> Database = new HashMap<>();

    public void createAccount(String type, String accNo, String name, BigDecimal initialDeposit) {
        BankAccount account;
        if ("SAVINGS".equalsIgnoreCase(type)) {
            account = new SavingsAccount(accNo, name, initialDeposit);
        } else if ("FD".equalsIgnoreCase(type)) {
            account = new FixedDepositAccount(accNo, name, initialDeposit);
        } else if ("SALARY".equalsIgnoreCase(type)) {
            account = new SalaryAccount(accNo, name, initialDeposit);
        } else {
            account = new BankAccount(accNo, name, initialDeposit);
        }
        saveToDatabase(account);
        emailNotification.sendEmail(name, "Account created: " + accNo);
    }

    public void deposit(String accNo, BigDecimal amount) {
        BankAccount account = Database.get(accNo);
        account.deposit(amount, accNo);
        saveToDatabase(account);
        emailNotification.sendEmail(account.getHolderName(), "Deposited: " + amount);
    }

    public void withdraw(String accNo, BigDecimal amount) {
        BankAccount account = Database.get(accNo);
        account.withdraw(amount, accNo);
        saveToDatabase(account);
        emailNotification.sendEmail(account.getHolderName(), "Withdrawn: " + amount);
    }

    public void processPayment(String paymentType, BigDecimal amount, String accNo) {
        if ("UPI".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing UPI payment of " + amount);
            withdraw(accNo, amount);
        } else if ("CARD".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing  Card payment of " + amount);
            withdraw(accNo, amount);
        } else if ("NETBANKING".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing Net Banking payment of " + amount);
            withdraw(accNo, amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
    }

    private void saveToDatabase(BankAccount account) {
        Database.put(account.getAccountNumber(), account);
        System.out.println("Saved account " + account.getAccountNumber());
    }

    public void transfer(BigDecimal amount, String SourceAccount, String targetAccount) {
        withdraw(SourceAccount, amount);
        deposit(targetAccount, amount);
        System.out.println("Transferred " + amount + " to " + targetAccount);
    }

    public BigDecimal getBalance(String accNo) {
        BankAccount account = Database.get(accNo);
        return account.getBalance();
    }
}
