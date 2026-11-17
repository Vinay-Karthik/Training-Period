package Adapter_Pattern.adapter;

import Adapter_Pattern.external.RazorpayGateway;
import Adapter_Pattern.interfaces.PaymentProcessor;

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
