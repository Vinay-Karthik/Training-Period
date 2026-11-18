package Adapter_Pattern.With.adapter;

import Adapter_Pattern.With.external.PayPalGateway;
import Adapter_Pattern.With.interfaces.PaymentProcessor;

import java.util.Objects;

public class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway payPalGateway;
    private final String defaultCurrency;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this(payPalGateway, "INR");
    }

    public PayPalAdapter(PayPalGateway payPalGateway, String currency) {
        this.payPalGateway = Objects.requireNonNull(payPalGateway, "PayPalGateway cannot be null");
        this.defaultCurrency = Objects.requireNonNull(currency, "Currency cannot be null");
    }

    @Override
    public void payment(double amount) {
        payPalGateway.sendPayment(defaultCurrency, amount);
    }
}
