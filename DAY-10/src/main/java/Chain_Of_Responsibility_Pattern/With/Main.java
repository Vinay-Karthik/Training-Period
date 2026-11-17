package Chain_Of_Responsibility_Pattern.With;

public class Main {

    public static void main(String[] args) {

        Approver teller = new Teller();
        Approver branchManager = new BranchManager();
        Approver regionalManager = new RegionalManager();
        Approver headOffice = new HeadOffice();

        teller.setNext(branchManager).setNext(regionalManager).setNext(headOffice);

        teller.approve(new Request(5000, "Withdrawal", "Cash withdrawal"));
        teller.approve(new Request(50000, "Loan", "Personal loan"));
        teller.approve(new Request(500000, "Transfer", "Business transfer"));
        teller.approve(new Request(5000000, "Loan", "Home loan"));
    }
}
