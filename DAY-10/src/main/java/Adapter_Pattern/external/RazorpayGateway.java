package Adapter_Pattern.external;

public class RazorpayGateway {
    public void payAmount(double amount) {
        System.out.println("Processing PayPal payment...");
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Payment successful!");
    }
}
