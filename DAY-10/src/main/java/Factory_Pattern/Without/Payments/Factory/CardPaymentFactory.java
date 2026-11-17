package Factory_Pattern.Without.Payments.Factory;

import Factory_Pattern.Without.Payments.Interfaces.CardPayment;
import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class CardPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new CardPayment();
    }
}
