package DRY_Principle.Without;

// WITHOUT DRY - the same tax/discount/final-amount logic is
// copy-pasted for every customer. Change the tax rate? Edit 3 places.
public class Main {

    public static void main(String[] args) {

        // Regular Customer
        double regularPrice = 1000;
        double regularTax = regularPrice * 0.18;
        double regularDiscount = regularPrice * 0.05;
        double regularFinalAmount = regularPrice + regularTax - regularDiscount;
        System.out.println("Regular Customer Bill: " + regularFinalAmount);

        // Premium Customer
        double premiumPrice = 2000;
        double premiumTax = premiumPrice * 0.18;
        double premiumDiscount = premiumPrice * 0.10;
        double premiumFinalAmount = premiumPrice + premiumTax - premiumDiscount;
        System.out.println("Premium Customer Bill: " + premiumFinalAmount);

        // VIP Customer
        double vipPrice = 5000;
        double vipTax = vipPrice * 0.18;
        double vipDiscount = vipPrice * 0.20;
        double vipFinalAmount = vipPrice + vipTax - vipDiscount;
        System.out.println("VIP Customer Bill: " + vipFinalAmount);
    }
}