package Factory_Pattern.With.Payments;

import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid using CardPayment");
    }
}
