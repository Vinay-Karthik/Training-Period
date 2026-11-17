package Adapter_Pattern.adapter;

import Adapter_Pattern.external.PayPalGateway;
import Adapter_Pattern.interfaces.PaymentProcessor;

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
