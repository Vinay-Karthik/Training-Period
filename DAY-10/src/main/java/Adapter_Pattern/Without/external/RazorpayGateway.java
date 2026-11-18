package Adapter_Pattern.Without.external;

public class RazorpayGateway {
    public void makePayment(double inrAmount) {
        System.out.println("Processing payment of INR " + inrAmount + " via Razorpay API.");
    }
}
