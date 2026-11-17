package Abstract_Factory_Pattern.Without.Notification;

import Abstract_Factory_Pattern.Without.Notification.Interfaces.TransactionNotification;

public class SmsTransactionNotification implements TransactionNotification {

    @Override
    public void sendTransactionAlert(String message) {
        System.out.println("SMS Transaction Alert:");
        System.out.println(message);
        System.out.println();
    }
}
