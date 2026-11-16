package Law_of_Demeter;


public class BankAccount {

    private Transaction transaction;

    public BankAccount(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
