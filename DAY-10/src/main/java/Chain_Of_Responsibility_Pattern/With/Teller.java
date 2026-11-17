package Chain_Of_Responsibility_Pattern.With;

public class Teller extends Approver {

    private static final double LIMIT = 10000;

    @Override
    public void approve(Request request) {
        System.out.println("\nTeller received: " + request);
        if (request.getAmount() <= LIMIT) {
            System.out.println("Approved by Teller (limit: Rs.10,000)");
        } else {
            System.out.println("Amount exceeds teller limit. Forwarding...");
            forward(request);
        }
    }
}
