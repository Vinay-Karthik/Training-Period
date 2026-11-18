package Factory_Pattern.Without;

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI Payment.");
    }
}
