package Factory_Pattern.With.factory;

import Factory_Pattern.With.payment.CardPayment;
import Factory_Pattern.With.payment.Payment;

public class CardPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new CardPayment();
    }
}
