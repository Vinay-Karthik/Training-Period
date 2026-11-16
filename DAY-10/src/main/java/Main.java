
public class Main {
    public static void main(String[] args) {

        PaymentService paymentService =
                new PaymentService();

        // Credit Card Payment
        paymentService.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        paymentService.checkout(1500);

        // UPI Payment
        paymentService.setPaymentStrategy(new UpiPayment("user@okaxis"));
        paymentService.checkout(2500);
        // PayPal Payment
        paymentService.setPaymentStrategy(new PayPalPayment("user@gmail.com"));
        paymentService.checkout(3500);
    }
}
