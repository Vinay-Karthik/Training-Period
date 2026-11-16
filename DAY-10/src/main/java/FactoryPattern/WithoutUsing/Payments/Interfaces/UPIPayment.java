package FactoryPattern.WithoutUsing.Payments.Interfaces;

public class UPIPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid" + amount + "via UPIPayment");
    }
}

