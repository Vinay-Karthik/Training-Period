package Law_Of_Demeter_Principle.Without;


public class TransactionDetails {

    private final double amount;
    private final String transactionData;

    public TransactionDetails(double amount, String transactionType) {
        this.amount = amount;
        this.transactionData = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionData() {
        return transactionData;
    }
}
