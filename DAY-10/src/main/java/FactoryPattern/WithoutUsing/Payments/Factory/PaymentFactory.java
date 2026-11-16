package FactoryPattern.WithoutUsing.Payments.Factory;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public abstract class PaymentFactory {

    protected abstract Payment createPayment();

    public void processPayment(double amount) {
        Payment payment = createPayment();
        payment.pay(amount);
    }
}
