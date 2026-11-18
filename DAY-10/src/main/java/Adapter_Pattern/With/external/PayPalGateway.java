package Adapter_Pattern.With.external;


public class PayPalGateway {

    public void sendPayment(String currency, double amount) {
        System.out.println("Processing PayPal payment...");
        System.out.println("Currency: " + currency);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Payment successful!");
    }
}
