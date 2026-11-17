package Factory_Pattern.Without.Payments;

import Factory_Pattern.Without.Payments.Factory.CardPaymentFactory;
import Factory_Pattern.Without.Payments.Factory.NetBankingPaymentFactory;
import Factory_Pattern.Without.Payments.Factory.PaymentFactory;
import Factory_Pattern.Without.Payments.Factory.UPIPaymentFactory;

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
