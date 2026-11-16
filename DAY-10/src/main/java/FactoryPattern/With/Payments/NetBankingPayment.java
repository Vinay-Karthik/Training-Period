package FactoryPattern.With.Payments;

import FactoryPattern.WithoutUsing.Payments.Interfaces.Payment;

public class NetBankingPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Payed using NetBanking");
    }
}
