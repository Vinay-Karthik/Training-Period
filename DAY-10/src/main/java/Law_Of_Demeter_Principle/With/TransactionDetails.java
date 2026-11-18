package Law_Of_Demeter_Principle.With;


public class TransactionDetails {

    private final double amount;
    private final String transactionType;

    public TransactionDetails(double amount, String transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
