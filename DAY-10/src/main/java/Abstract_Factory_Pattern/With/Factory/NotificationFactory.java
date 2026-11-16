package Abstract_Factory_Pattern.With.Factory;

import Abstract_Factory_Pattern.With.Notification.Interfaces.AccountNotification;
import Abstract_Factory_Pattern.With.Notification.Interfaces.TransactionNotification;

public interface NotificationFactory {
    TransactionNotification createTransactionNotification();

    AccountNotification createAccountNotification();
}
