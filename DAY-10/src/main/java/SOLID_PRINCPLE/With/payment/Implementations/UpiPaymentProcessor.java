package SOLID_PRINCPLE.With.payment.Implementations;

import SOLID_PRINCPLE.With.payment.PaymentProcessor;

import java.math.BigDecimal;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public String getPaymentType() {
        return "UPI";
    }

    @Override
    public void process(BigDecimal amount) {
        System.out.println("Processing UPI payment of " + amount);
    }
}
