package Adapter_Pattern.With.service;


import Adapter_Pattern.With.interfaces.PaymentProcessor;

import java.util.Objects;

public class BankingPaymentService {

    private final PaymentProcessor paymentProcessor;

    public BankingPaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = Objects.requireNonNull(paymentProcessor, "PaymentProcessor cannot be null");
    }

    public void makePayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        paymentProcessor.payment(amount);
    }
}
