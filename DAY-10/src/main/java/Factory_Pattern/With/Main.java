package Factory_Pattern.With;

import Factory_Pattern.With.factory.CardPaymentFactory;
import Factory_Pattern.With.factory.NetBankingPaymentFactory;
import Factory_Pattern.With.factory.PaymentFactory;
import Factory_Pattern.With.factory.UpiPaymentFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Payment Processing (With Factory Pattern) ===");

        PaymentFactory upiFactory = new UpiPaymentFactory();
        upiFactory.processPayment(1500.00);

        PaymentFactory cardFactory = new CardPaymentFactory();
        cardFactory.processPayment(4200.50);

        PaymentFactory netBankingFactory = new NetBankingPaymentFactory();
        netBankingFactory.processPayment(10000.00);
    }
}
