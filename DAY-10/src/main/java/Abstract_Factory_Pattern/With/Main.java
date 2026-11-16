package Abstract_Factory_Pattern.With;

import Abstract_Factory_Pattern.With.Factory.EmailNotificationFactory;
import Abstract_Factory_Pattern.With.Factory.NotificationFactory;
import Abstract_Factory_Pattern.With.Factory.SmsNotificationFactory;
import Abstract_Factory_Pattern.With.serivce.BankNotificationService;

public class Main {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        BankNotificationService emailService = new BankNotificationService(emailFactory);
        emailService.notifyAccount("Your bank account has been created successfully");
        emailService.notifyTransaction("Your account has been credited with Rs. 5000");

        NotificationFactory smsFactory = new SmsNotificationFactory();
        BankNotificationService smsService = new BankNotificationService(smsFactory);
        smsService.notifyAccount("Your bank account has been created successfully");
        smsService.notifyTransaction("Your account has been credited with Rs. 3000");
    }
}
