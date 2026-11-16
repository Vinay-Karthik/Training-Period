package Law_of_Demeter.With;



public class Transaction {

    private TransactionDetails details;

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

