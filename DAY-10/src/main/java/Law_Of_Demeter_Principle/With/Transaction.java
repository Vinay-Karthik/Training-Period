package Law_Of_Demeter_Principle.With;


public class Transaction {

    private final TransactionDetails details;

    public Transaction(TransactionDetails details) {
        this.details = details;
    }

    public double getAmount() {
        return details.getAmount();
    }

    public String getTransactionType() {
        return details.getTransactionType();
    }
}

