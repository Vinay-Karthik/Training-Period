package FactoryPattern.With;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class PaymentService {
    public void payment(Payment payment, double amount) {
        payment.pay(amount);
    }
}
