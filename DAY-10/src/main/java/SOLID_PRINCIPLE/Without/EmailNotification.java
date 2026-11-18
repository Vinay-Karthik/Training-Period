package SOLID_Principle.Without;

public class EmailNotification {
    public void sendEmail(String recipient, String message) {
        System.out.println("[EMAIL to " + recipient + "]: " + message);
    }
}