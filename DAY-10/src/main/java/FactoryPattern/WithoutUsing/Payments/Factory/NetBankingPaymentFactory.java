package FactoryPattern.WithoutUsing.Payments.Factory;

import FactoryPattern.WithoutUsing.Payments.Interfaces.NetBankingPayment;
import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class NetBankingPaymentFactory extends PaymentFactory {

    @Override
    protected Payment createPayment() {
        return new NetBankingPayment();
    }
}
