package Factory_Pattern.With.payment;

public class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Card Payment.");
    }
}
