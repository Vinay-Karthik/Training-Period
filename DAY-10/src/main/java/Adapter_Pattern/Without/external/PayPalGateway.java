package Adapter_Pattern.Without.external;

public class PayPalGateway {
    public void sendPayment(double amountInUsd) {
        System.out.println("Processing payment of $" + amountInUsd + " via PayPal API.");
    }
}
