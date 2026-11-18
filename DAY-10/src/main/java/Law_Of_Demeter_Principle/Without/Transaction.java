package Law_Of_Demeter_Principle.Without;


public class Transaction {

    private final TransactionDetails details;

    public Transaction(TransactionDetails details) {
        this.details = details;
    }

    public TransactionDetails getDetails() {
        return details;
    }
}

