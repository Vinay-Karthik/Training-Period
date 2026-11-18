package Factory_Pattern.With.factory;

import Factory_Pattern.With.payment.Payment;

public abstract class PaymentFactory {
    protected abstract Payment createPayment();

    public void processPayment(double amount) {
        Payment payment = createPayment();
        payment.pay(amount);
    }
}
