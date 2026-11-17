package Factory_Pattern.With.Payments;

import Factory_Pattern.Without.Payments.Interfaces.Payment;

public class NetBankingPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Payed using NetBanking");
    }
}
