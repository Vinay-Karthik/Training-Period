package Abstract_Factory_Pattern.Without.Notification;

import Abstract_Factory_Pattern.Without.Notification.Interfaces.AccountNotification;

public class EmailAccountNotification implements AccountNotification {
    @Override
    public void sendAccountAlert(String message) {
        System.out.println("Email Account Alert:");
        System.out.println(message);
        System.out.println();
    }
}
