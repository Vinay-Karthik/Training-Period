package Adapter_Pattern.Without;

import Adapter_Pattern.Without.service.BankingPaymentService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Payment Processing (Without Adapter Pattern) ===");
        BankingPaymentService service = new BankingPaymentService();

        service.processPayment("RAZORPAY", 3000);
        System.out.println();
        service.processPayment("PAYPAL", 4000);
        System.out.println();
        service.processPayment("STRIPE", 2000);
    }
}
