package Factory_Pattern.With.factory;

import Factory_Pattern.With.payment.NetBankingPayment;
import Factory_Pattern.With.payment.Payment;

public class NetBankingPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new NetBankingPayment();
    }
}
