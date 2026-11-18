package Decorator_Pattern.With.decorator;

import Decorator_Pattern.With.interfaces.BankAccount;

public class SmsAlertDecorator extends BankAccountDecorator {

    public SmsAlertDecorator(BankAccount wrappedAccount) {
        super(wrappedAccount);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("SMS Alert: Rs. " + String.format("%.0f", amount) + " deposited successfully.");
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        System.out.println("SMS Alert: Rs. " + String.format("%.0f", amount) + " withdrawn successfully.");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + SMS Alerts";
    }
}
