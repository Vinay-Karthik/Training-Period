package Chain_Of_Responsibility_Pattern.With;

public class BranchManager extends BankStaff {

    private static final double LIMIT = 100000;

    @Override
    public void approve(CustomerRequest request) {
        System.out.println("Branch Manager received: " + request);
        if (request.amount() <= LIMIT) {
            System.out.println("Approved by Branch Manager (limit: Rs.1,00,000)");
        } else {
            System.out.println("Amount exceeds branch manager limit. Forwarding...");
            forward(request);
        }
    }
}
