package Chain_Of_Responsibility_Pattern.With;

public class HeadOffice extends BankStaff {

    @Override
    public void approve(CustomerRequest request) {
        System.out.println("Head Office received: " + request);
        System.out.println("Approved by Head Office (no limit)");
    }
}
