package Adapter_Pattern.banking;

import Adapter_Pattern.interfaces.PaymentProcessor;


public class BankPaymentProcessor implements PaymentProcessor {

    @Override
    public void payment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        System.out.println("Using Bankpayment processor");
    }
}
