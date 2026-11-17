package Chain_Of_Responsibility_Pattern.With;

public abstract class BankStaff {

    protected BankStaff next;

    public BankStaff setNext(BankStaff next) {
        this.next = next;
        return next;
    }

    public abstract void approve(CustomerRequest request);

    protected void forward(CustomerRequest request) {
        if (next != null) {
            next.approve(request);
        } else {
            System.out.println("Request cannot be processed - no authority available.");
        }
    }
}
