package Factory_Pattern.With;

import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class PaymentService {
    public void payment(Payment payment, double amount) {
        payment.pay(amount);
    }
}
