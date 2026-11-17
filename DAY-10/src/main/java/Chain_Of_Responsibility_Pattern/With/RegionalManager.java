package Chain_Of_Responsibility_Pattern.With;

public class RegionalManager extends Approver {

    private static final double LIMIT = 1000000;

    @Override
    public void approve(Request request) {
        System.out.println("Regional Manager received: " + request);
        if (request.getAmount() <= LIMIT) {
            System.out.println("Approved by Regional Manager (limit: Rs.10,00,000)");
        } else {
            System.out.println("Amount exceeds regional manager limit. Forwarding...");
            forward(request);
        }
    }
}
