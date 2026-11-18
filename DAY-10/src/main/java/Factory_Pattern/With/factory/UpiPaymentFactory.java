package Factory_Pattern.With.factory;

import Factory_Pattern.With.payment.Payment;
import Factory_Pattern.With.payment.UpiPayment;

public class UpiPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new UpiPayment();
    }
}
