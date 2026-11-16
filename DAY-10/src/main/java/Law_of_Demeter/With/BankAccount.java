package Law_of_Demeter.With;



public class BankAccount {

    private Transaction transaction;

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
