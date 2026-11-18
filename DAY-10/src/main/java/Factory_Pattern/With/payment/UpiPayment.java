package Factory_Pattern.With.payment;

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI Payment.");
    }
}
