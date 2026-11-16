package Decorator_Pattern.decorator;


import Decorator_Pattern.interfaces.BankAccount;

public class EmailNotificationDecorator extends BankAccountDecorator {

    public EmailNotificationDecorator(BankAccount wrappedAccount) {
        super(wrappedAccount);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Email Notification: Deposit of Rs. " + String.format("%.0f", amount) + " successful.");
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        System.out.println("Email Notification: Withdrawal of Rs. " + String.format("%.0f", amount) + " successful.");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Email Notifications";
    }
}
