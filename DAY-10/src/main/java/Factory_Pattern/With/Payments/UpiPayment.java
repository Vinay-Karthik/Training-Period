package Factory_Pattern.With.Payments;

import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Payed using Upi");
    }
}
