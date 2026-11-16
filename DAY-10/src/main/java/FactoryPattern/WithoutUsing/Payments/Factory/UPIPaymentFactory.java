package FactoryPattern.WithoutUsing.Payments.Factory;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;
import FactoryPattern.WithoutUsing.Payments.Interfaces.UPIPayment;

public class UPIPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new UPIPayment();
    }
}
