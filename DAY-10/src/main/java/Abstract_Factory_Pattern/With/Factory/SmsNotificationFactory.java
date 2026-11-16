package Abstract_Factory_Pattern.With.Factory;


import Abstract_Factory_Pattern.With.Notification.Interfaces.AccountNotification;
import Abstract_Factory_Pattern.With.Notification.Interfaces.TransactionNotification;
import Abstract_Factory_Pattern.With.Notification.SmsAccountNotification;
import Abstract_Factory_Pattern.With.Notification.SmsTransactionNotification;

public class SmsNotificationFactory implements NotificationFactory {
    @Override
    public TransactionNotification createTransactionNotification() {
        return new SmsTransactionNotification();
    }

    @Override
    public AccountNotification createAccountNotification() {
        return new SmsAccountNotification();
    }
}
