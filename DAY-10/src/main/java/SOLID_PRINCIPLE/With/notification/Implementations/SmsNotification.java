package SOLID_PRINCIPLE.With.notification.Implementations;

import SOLID_PRINCIPLE.With.notification.NotificationSender;

public class SmsNotification implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS to " + recipient + "]: " + message);
    }
}
