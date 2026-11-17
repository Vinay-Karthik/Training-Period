package SOLID_PRINCIPLE.With.payment.Implementations;

import SOLID_PRINCIPLE.With.payment.PaymentProcessor;

import java.math.BigDecimal;

public class NetBankingPaymentProcessor implements PaymentProcessor {
    @Override
    public String getPaymentType() {
        return "NETBANKING";
    }

    @Override
    public void process(BigDecimal amount) {
        System.out.println("Processing NETBANKING payment of " + amount);
    }
}
