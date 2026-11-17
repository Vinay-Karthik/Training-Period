package Factory_Pattern.Without.Payments.Interfaces;

public class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid" + amount + "via CardPayment");
    }
}
