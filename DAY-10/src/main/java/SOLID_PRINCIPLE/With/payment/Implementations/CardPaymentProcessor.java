package SOLID_Principle.With.payment.Implementations;

import SOLID_Principle.With.payment.PaymentProcessor;

import java.math.BigDecimal;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public String getPaymentType() {
        return "CARD";
    }

    @Override
    public void process(BigDecimal amount) {
        System.out.println("Processing CARD payment of " + amount);
    }
}
