package Factory_Pattern.Without.Payments.Factory;

import Factory_Pattern.Without.Payments.Interfaces.Payment;
import Factory_Pattern.Without.Payments.Interfaces.UPIPayment;

public class UPIPaymentFactory extends PaymentFactory {
    @Override
    protected Payment createPayment() {
        return new UPIPayment();
    }
}
