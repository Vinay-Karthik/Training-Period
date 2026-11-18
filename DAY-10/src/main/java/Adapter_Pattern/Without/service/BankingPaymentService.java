package Adapter_Pattern.Without.service;

import Adapter_Pattern.Without.external.PayPalGateway;
import Adapter_Pattern.Without.external.RazorpayGateway;
import Adapter_Pattern.Without.external.StripeGateway;

// Without Adapter: Tightly coupled to all concrete external gateways with differing method signatures
public class BankingPaymentService {
    private final PayPalGateway payPal = new PayPalGateway();
    private final RazorpayGateway razorpay = new RazorpayGateway();
    private final StripeGateway stripe = new StripeGateway();

    public void processPayment(String gatewayType, double amount) {
        System.out.println("Initiating " + gatewayType + " payment of " + amount);
        if ("PAYPAL".equalsIgnoreCase(gatewayType)) {
            payPal.sendPayment(amount);
        } else if ("RAZORPAY".equalsIgnoreCase(gatewayType)) {
            razorpay.makePayment(amount);
        } else if ("STRIPE".equalsIgnoreCase(gatewayType)) {
            stripe.charge(amount * 100);
        } else {
            System.out.println("Unsupported gateway: " + gatewayType);
        }
    }
}
