package SOLID_Principle.With.notification.Implementations;

import SOLID_Principle.With.notification.NotificationSender;

public class SmsNotification implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS to " + recipient + "]: " + message);
    }
}
