package Adapter_Pattern.With;


import Adapter_Pattern.With.adapter.PayPalAdapter;
import Adapter_Pattern.With.adapter.RazorpayAdapter;
import Adapter_Pattern.With.adapter.StripeAdapter;
import Adapter_Pattern.With.banking.BankPaymentProcessor;
import Adapter_Pattern.With.external.PayPalGateway;
import Adapter_Pattern.With.external.RazorpayGateway;
import Adapter_Pattern.With.external.StripeGateway;
import Adapter_Pattern.With.interfaces.PaymentProcessor;
import Adapter_Pattern.With.service.BankingPaymentService;

public class Main {

    public static void main(String[] args) {

        // Scenario 1: Bank Payment
        System.out.println("--- Scenario 1: Bank Payment ---");
        PaymentProcessor bankProcessor = new BankPaymentProcessor();
        BankingPaymentService bankService = new BankingPaymentService(bankProcessor);
        bankService.makePayment(5000);

        System.out.println();

        // Scenario 2: Razorpay Payment
        System.out.println("--- Scenario 2: Razorpay Payment ---");
        PaymentProcessor razorpayProcessor = new RazorpayAdapter(new RazorpayGateway());
        BankingPaymentService razorpayService = new BankingPaymentService(razorpayProcessor);
        razorpayService.makePayment(3000);

        System.out.println();

        // Scenario 3: PayPal Payment
        System.out.println("--- Scenario 3: PayPal Payment ---");
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        BankingPaymentService paypalService = new BankingPaymentService(paypalProcessor);
        paypalService.makePayment(4000);

        System.out.println();

        // Scenario 4: Stripe Payment
        System.out.println("--- Scenario 4: Stripe Payment ---");
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        BankingPaymentService stripeService = new BankingPaymentService(stripeProcessor);
        stripeService.makePayment(2000);
    }
}
