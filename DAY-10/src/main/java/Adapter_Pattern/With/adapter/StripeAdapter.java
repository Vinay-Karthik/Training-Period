package Adapter_Pattern.With.adapter;

import Adapter_Pattern.With.external.StripeGateway;
import Adapter_Pattern.With.interfaces.PaymentProcessor;

import java.util.Objects;

public class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = Objects.requireNonNull(stripeGateway, "StripeGateway cannot be null");
    }

    @Override
    public void payment(double amount) {
        stripeGateway.chargeCard(amount);
    }
}
