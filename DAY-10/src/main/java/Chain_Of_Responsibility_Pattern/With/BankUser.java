package Chain_Of_Responsibility_Pattern.With;

public class BankUser extends BankStaff {

    private static final double LIMIT = 10000;

    @Override
    public void approve(CustomerRequest request) {
        System.out.println("\nBank Teller received: " + request);
        if (request.amount() <= LIMIT) {
            System.out.println("Approved by Bank Teller (limit: Rs.10,000)");
        } else {
            System.out.println("Amount exceeds teller limit. Forwarding...");
            forward(request);
        }
    }
}
