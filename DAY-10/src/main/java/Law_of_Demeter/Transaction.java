package Law_of_Demeter;


public class Transaction {

    private TransactionDetails details;

    public Transaction(TransactionDetails details) {
        this.details = details;
    }

    public TransactionDetails getDetails() {
        return details;
    }
}

