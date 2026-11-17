package Chain_Of_Responsibility_Pattern.With;

public class Main {

    public static void main(String[] args) {

        BankStaff user = new BankUser();
        BankStaff branchManager = new BranchManager();
        BankStaff regionalManager = new RegionalManager();
        BankStaff headOffice = new HeadOffice();

        user.setNext(branchManager).setNext(regionalManager).setNext(headOffice);

        user.approve(new CustomerRequest(5000, "Withdrawal", "Cash withdrawal"));
        user.approve(new CustomerRequest(50000, "Loan", "Personal loan"));
//        user.approve(new CustomerRequest(500000, "Transfer", "Business transfer"));
//        user.approve(new CustomerRequest(5000000, "Loan", "Home loan"));
    }
}
