package Factory_Pattern.With;

import Factory_Pattern.With.Payments.CardPayment;
import Factory_Pattern.With.Payments.NetBankingPayment;
import Factory_Pattern.With.Payments.UpiPayment;

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.payment(new UpiPayment(), 1500.00);
        service.payment(new CardPayment(), 4200.50);
        service.payment(new NetBankingPayment(), 10000.00);
    }
}
