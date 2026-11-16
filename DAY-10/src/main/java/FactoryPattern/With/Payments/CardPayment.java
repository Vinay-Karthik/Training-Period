package FactoryPattern.With.Payments;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid using CardPayment");
    }
}
