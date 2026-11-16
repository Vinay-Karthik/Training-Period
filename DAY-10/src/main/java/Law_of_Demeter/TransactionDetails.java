package Law_of_Demeter;


public class TransactionDetails {

    private double amount;
    private String transactionData;

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
