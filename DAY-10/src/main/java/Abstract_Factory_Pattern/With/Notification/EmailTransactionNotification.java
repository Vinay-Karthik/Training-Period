package Abstract_Factory_Pattern.With.Notification;

import Abstract_Factory_Pattern.With.Notification.Interfaces.TransactionNotification;

public class EmailTransactionNotification implements TransactionNotification {
    @Override
    public void sendTransactionAlert(String message) {
        System.out.println("Email Transaction Alert:");
        System.out.println(message);
        System.out.println();
    }
}
