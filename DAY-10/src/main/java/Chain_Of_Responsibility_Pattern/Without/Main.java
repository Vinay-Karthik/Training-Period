package Chain_Of_Responsibility_Pattern.Without;

public class Main {

    public static void main(String[] args) {

        BankCounter counter = new BankCounter();

        counter.process(new CustomerRequest(5000, "Withdrawal", "Cash withdrawal"));
        counter.process(new CustomerRequest(50000, "Loan", "Personal loan"));
        counter.process(new CustomerRequest(500000, "Transfer", "Business transfer"));
        counter.process(new CustomerRequest(5000000, "Loan", "Home loan"));
    }
}
