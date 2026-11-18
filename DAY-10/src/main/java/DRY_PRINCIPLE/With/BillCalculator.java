package DRY_Principle.With;

public class BillCalculator {

    private static final double TAX_RATE = 0.18;

    public double calculateTax(double price) {
        return price * TAX_RATE;
    }

    public double calculateDiscount(double price, double discountPercentage) {
        return price * discountPercentage;
    }

    public double calculateFinalAmount(double price, double discountPercentage) {
        double tax = calculateTax(price);
        double discount = calculateDiscount(price, discountPercentage);
        return price + tax - discount;
    }
}
