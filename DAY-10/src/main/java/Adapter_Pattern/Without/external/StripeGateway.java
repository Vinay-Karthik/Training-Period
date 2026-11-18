package Adapter_Pattern.Without.external;

public class StripeGateway {
    public void charge(double amountInCents) {
        System.out.println("Processing charge of " + amountInCents + " cents via Stripe API.");
    }
}
