package Abstract_Factory_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        BankNotificationService emailService = new BankNotificationService("email");
        emailService.notifyAccount("Your bank account has been created successfully");
        emailService.notifyTransaction("Your account has been credited with Rs. 5000");

        BankNotificationService smsService = new BankNotificationService("sms");
        smsService.notifyAccount("Your bank account has been created successfully");
        smsService.notifyTransaction("Your account has been credited with Rs. 3000");
    }
}
