package SOLID_PRINCIPLE.With.notification.Implementations;

import SOLID_PRINCIPLE.With.notification.NotificationSender;

public class EmailNotification implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[EMAIL to " + recipient + "]: " + message);
    }
}
