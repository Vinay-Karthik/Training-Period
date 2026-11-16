package FactoryPattern.With.Payments;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Payed using Upi");
    }
}
