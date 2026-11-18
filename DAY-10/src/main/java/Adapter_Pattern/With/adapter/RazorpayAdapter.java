package Adapter_Pattern.With.adapter;

import Adapter_Pattern.With.external.RazorpayGateway;
import Adapter_Pattern.With.interfaces.PaymentProcessor;

import java.util.Objects;

public class RazorpayAdapter implements PaymentProcessor {
    private final RazorpayGateway razorpayGateway;

    public RazorpayAdapter(RazorpayGateway razorpayGateway) {
        this.razorpayGateway = Objects.requireNonNull(razorpayGateway, "RazorpayGateway cannot be null");
    }

    @Override
    public void payment(double amount) {
        razorpayGateway.payAmount(amount);
    }
}
