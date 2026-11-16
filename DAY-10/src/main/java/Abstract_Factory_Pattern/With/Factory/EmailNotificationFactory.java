package Abstract_Factory_Pattern.With.Factory;

import Abstract_Factory_Pattern.With.Notification.EmailAccountNotification;
import Abstract_Factory_Pattern.With.Notification.EmailTransactionNotification;
import Abstract_Factory_Pattern.With.Notification.Interfaces.AccountNotification;
import Abstract_Factory_Pattern.With.Notification.Interfaces.TransactionNotification;

public class EmailNotificationFactory implements NotificationFactory {
    @Override
    public TransactionNotification createTransactionNotification() {
        return new EmailTransactionNotification();
    }

    @Override
    public AccountNotification createAccountNotification() {
        return new EmailAccountNotification();
    }
}
