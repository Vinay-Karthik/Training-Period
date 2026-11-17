package SOLID_PRINCIPLE.With.payment;

import java.math.BigDecimal;

// OCP: new payment types are added as new classes, not by editing BankService
public interface PaymentProcessor {
    String getPaymentType();

    void process(BigDecimal amount);
}
