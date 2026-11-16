package FactoryPattern.With;

import FactoryPattern.With.Payments.CardPayment;
import FactoryPattern.With.Payments.NetBankingPayment;
import FactoryPattern.With.Payments.UpiPayment;

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.payment(new UpiPayment(), 1500.00);
        service.payment(new CardPayment(), 4200.50);
        service.payment(new NetBankingPayment(), 10000.00);
    }
}
