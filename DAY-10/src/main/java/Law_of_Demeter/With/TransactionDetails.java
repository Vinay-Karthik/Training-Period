package Law_of_Demeter.With;


public class TransactionDetails {

    private double amount;
    private String transactionType;

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
