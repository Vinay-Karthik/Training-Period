package Factory_Pattern.Without.Payments.Factory;

import Factory_Pattern.Without.Payments.Interfaces.Payment;

public abstract class PaymentFactory {

    protected abstract Payment createPayment();

    public void processPayment(double amount) {
        Payment payment = createPayment();
        payment.pay(amount);
    }
}
