package SOLID_Principle.With.notification;

// DIP: depend on this abstraction, not on EmailNotification directly
public interface NotificationSender {
    void send(String recipient, String message);
}
