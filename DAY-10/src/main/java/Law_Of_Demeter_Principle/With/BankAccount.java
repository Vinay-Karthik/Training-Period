package Law_Of_Demeter_Principle.With;


public class BankAccount {

    private final Transaction transaction;

    public BankAccount(Transaction transaction) {
        this.transaction = transaction;
    }

    public double getTransactionAmount() {
        return transaction.getAmount();
    }

    public String getTransactionType() {
        return transaction.getTransactionType();
    }
}
