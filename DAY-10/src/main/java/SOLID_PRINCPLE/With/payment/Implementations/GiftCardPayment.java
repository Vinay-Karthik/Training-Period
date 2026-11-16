package SOLID_PRINCPLE.With.payment.Implementations;

import SOLID_PRINCPLE.With.payment.PaymentProcessor;

import java.math.BigDecimal;

public class GiftCardPayment implements PaymentProcessor {
    @Override
    public String getPaymentType() {
        return "GIFT_CARD";
    }

    @Override
    public void process(BigDecimal amount) {
        System.out.println("Processing gift card payment");
    }
}
