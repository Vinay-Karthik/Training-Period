package FactoryPattern.WithoutUsing.Payments;

import FactoryPattern.WithoutUsing.Payments.Factory.CardPaymentFactory;
import FactoryPattern.WithoutUsing.Payments.Factory.NetBankingPaymentFactory;
import FactoryPattern.WithoutUsing.Payments.Factory.PaymentFactory;
import FactoryPattern.WithoutUsing.Payments.Factory.UPIPaymentFactory;

public class Main {
    public static void main(String[] args) {
        PaymentFactory upiFactory = new UPIPaymentFactory();
        upiFactory.processPayment(1500.00);

        PaymentFactory cardFactory = new CardPaymentFactory();
        cardFactory.processPayment(4200.50);

        PaymentFactory netBankingFactory = new NetBankingPaymentFactory();
        netBankingFactory.processPayment(10000.00);
    }
}
