package Factory_Pattern.Without;

public class NetBankingPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Net Banking Payment.");
    }
}
