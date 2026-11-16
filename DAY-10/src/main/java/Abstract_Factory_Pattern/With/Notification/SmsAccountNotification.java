package Abstract_Factory_Pattern.With.Notification;

import Abstract_Factory_Pattern.With.Notification.Interfaces.AccountNotification;

public class SmsAccountNotification implements AccountNotification {
    @Override
    public void sendAccountAlert(String message) {
        System.out.println("SMS Account Alert:");
        System.out.println(message);
        System.out.println();
    }
}
