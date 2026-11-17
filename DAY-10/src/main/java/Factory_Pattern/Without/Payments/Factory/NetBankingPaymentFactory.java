package Factory_Pattern.Without.Payments.Factory;

import Factory_Pattern.Without.Payments.Interfaces.NetBankingPayment;
import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class NetBankingPaymentFactory extends PaymentFactory {

    @Override
    protected Payment createPayment() {
        return new NetBankingPayment();
    }
}
