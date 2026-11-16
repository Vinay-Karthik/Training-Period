package FactoryPattern.WithoutUsing.Payments.Factory;

import FactoryPattern.WithoutUsing.Payments.Interfaces.CardPayment;
import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class CardPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new CardPayment();
    }
}
