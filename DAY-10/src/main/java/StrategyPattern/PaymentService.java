package StrategyPattern;

public class PaymentService {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not selected!");
        }
        paymentStrategy.pay(amount);
    }
}
