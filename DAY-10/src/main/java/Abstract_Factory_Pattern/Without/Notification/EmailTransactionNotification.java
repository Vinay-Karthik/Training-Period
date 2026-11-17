package Abstract_Factory_Pattern.Without.Notification;

import Abstract_Factory_Pattern.Without.Notification.Interfaces.TransactionNotification;

public class EmailTransactionNotification implements TransactionNotification {
    @Override
    public void sendTransactionAlert(String message) {
        System.out.println("Email Transaction Alert:");
        System.out.println(message);
        System.out.println();
    }
}
