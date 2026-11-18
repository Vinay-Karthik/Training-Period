package Factory_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Payment Processing (Without Factory Pattern) ===");
        PaymentService service = new PaymentService();

        // Client is directly creating concrete payment objects without a factory
        service.processPayment(new UpiPayment(), 1500.00);
        service.processPayment(new CardPayment(), 4200.50);
        service.processPayment(new NetBankingPayment(), 10000.00);
    }
}
