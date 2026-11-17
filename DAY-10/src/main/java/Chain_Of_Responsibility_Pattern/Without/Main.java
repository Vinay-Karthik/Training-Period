package Chain_Of_Responsibility_Pattern.Without;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

        bank.process(new Request(5000, "Withdrawal", "Cash withdrawal"));
        bank.process(new Request(50000, "Loan", "Personal loan"));
        bank.process(new Request(500000, "Transfer", "Business transfer"));
        bank.process(new Request(5000000, "Loan", "Home loan"));
    }
}
