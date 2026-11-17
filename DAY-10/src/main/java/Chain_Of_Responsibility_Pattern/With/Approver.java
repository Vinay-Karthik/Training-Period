package Chain_Of_Responsibility_Pattern.With;

public abstract class Approver {

    protected Approver next;

    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    public abstract void approve(Request request);

    protected void forward(Request request) {
        if (next != null) {
            next.approve(request);
        } else {
            System.out.println("Request cannot be processed - no authority available.");
        }
    }
}
