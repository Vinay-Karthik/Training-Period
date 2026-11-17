package Abstract_Factory_Pattern.Without;

import Abstract_Factory_Pattern.Without.Notification.EmailAccountNotification;
import Abstract_Factory_Pattern.Without.Notification.EmailTransactionNotification;
import Abstract_Factory_Pattern.Without.Notification.Interfaces.AccountNotification;
import Abstract_Factory_Pattern.Without.Notification.Interfaces.TransactionNotification;
import Abstract_Factory_Pattern.Without.Notification.SmsAccountNotification;
import Abstract_Factory_Pattern.Without.Notification.SmsTransactionNotification;

public class BankNotificationService {

    private final TransactionNotification transactionNotification;
    private final AccountNotification accountNotification;

    public BankNotificationService(String channel) {
        if (channel.equalsIgnoreCase("email")) {
            this.transactionNotification = new EmailTransactionNotification();
            this.accountNotification = new EmailAccountNotification();
        } else if (channel.equalsIgnoreCase("sms")) {
            this.transactionNotification = new SmsTransactionNotification();
            this.accountNotification = new SmsAccountNotification();
        } else {
            throw new IllegalArgumentException("Unsupported channel: " + channel);
        }
    }

    public void notifyTransaction(String message) {
        transactionNotification.sendTransactionAlert(message);
    }

    public void notifyAccount(String message) {
        accountNotification.sendAccountAlert(message);
    }
}
