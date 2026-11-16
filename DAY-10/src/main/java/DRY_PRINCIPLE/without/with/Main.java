package DRY_PRINCIPLE.without.with;

public class Main {

    public static void main(String[] args) {

        BillCalculator calculator = new BillCalculator();

        // Regular Customer
        double regularBill = calculator.calculateFinalAmount(1000, 0.05);

        // Premium Customer
        double premiumBill = calculator.calculateFinalAmount(2000, 0.10);

        // VIP Customer
        double vipBill = calculator.calculateFinalAmount(5000, 0.20);

        // New Customer
        double newCustomerBill = calculator.calculateFinalAmount(3000, 0.15);

        System.out.println("Regular Customer Bill: " + regularBill);
        System.out.println("Premium Customer Bill: " + premiumBill);
        System.out.println("VIP Customer Bill: " + vipBill);
        System.out.println("New Customer Bill: " + newCustomerBill);
    }
}

