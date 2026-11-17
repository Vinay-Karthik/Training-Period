package Adapter_Pattern.external;

public class StripeGateway {

    public void chargeCard(double amountInPaise) {
        System.out.println("Processing PayPal payment...");
        System.out.println("Amount: Rs. " + amountInPaise);
        System.out.println("Payment successful!");
    }
}
