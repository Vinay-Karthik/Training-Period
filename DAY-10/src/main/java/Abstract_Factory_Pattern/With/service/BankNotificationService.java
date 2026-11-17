package Abstract_Factory_Pattern.With.service;


import Abstract_Factory_Pattern.With.Factory.NotificationFactory;
import Abstract_Factory_Pattern.With.Notification.Interfaces.AccountNotification;
import Abstract_Factory_Pattern.With.Notification.Interfaces.TransactionNotification;

public class BankNotificationService {

    private final TransactionNotification transactionNotification;
    private final AccountNotification accountNotification;

    public BankNotificationService(NotificationFactory factory) {
        this.transactionNotification = factory.createTransactionNotification();
        this.accountNotification = factory.createAccountNotification();
    }

    public void notifyTransaction(String message) {
        transactionNotification.sendTransactionAlert(message);
    }

    public void notifyAccount(String message) {
        accountNotification.sendAccountAlert(message);
    }
}
