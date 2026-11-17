package Chain_Of_Responsibility_Pattern.With;

public class HeadOffice extends Approver {

    @Override
    public void approve(Request request) {
        System.out.println("Head Office received: " + request);
        System.out.println("Approved by Head Office (no limit)");
    }
}
