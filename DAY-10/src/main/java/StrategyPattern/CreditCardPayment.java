package StrategyPattern;

public class CreditCardPayment implements PaymentStrategy {

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card payment...");
        System.out.println("Card: " + cardNumber);
        System.out.println("Amount paid: Rs. " + amount);
    }
}
