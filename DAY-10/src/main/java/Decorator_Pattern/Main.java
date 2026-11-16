package Decorator_Pattern;


import Decorator_Pattern.core.BasicBankAccount;
import Decorator_Pattern.decorator.EmailNotificationDecorator;
import Decorator_Pattern.decorator.SmsAlertDecorator;
import Decorator_Pattern.decorator.WhatsAppDecorator;
import Decorator_Pattern.interfaces.BankAccount;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SCENARIO 1: Basic Account ===");
        BankAccount basicAccount = new BasicBankAccount(10000);
        System.out.println("Account Type: " + basicAccount.getDescription());
        System.out.println("Balance: Rs. " + basicAccount.getBalance());

        System.out.println("\n=== SCENARIO 2: Account with SMS Alerts ===");
        BankAccount smsAccount = new SmsAlertDecorator(new BasicBankAccount(10000));
        smsAccount.deposit(1500);
        System.out.println("Updated balance is: " + smsAccount.getBalance());

        System.out.println("\n=== SCENARIO 3: Account with SMS and Email ===");
        BankAccount smsAndEmailAccount = new EmailNotificationDecorator(
                new SmsAlertDecorator(
                        new BasicBankAccount(10000)
                )
        );
        smsAndEmailAccount.withdraw(500);
        System.out.println("Updated balance is: " + smsAndEmailAccount.getBalance());

        System.out.println("\n--whatsAppDecorator pattern--");
        WhatsAppDecorator whatsAppDecorator = new WhatsAppDecorator(new BasicBankAccount(500000));
        whatsAppDecorator.promotion();
    }
}
