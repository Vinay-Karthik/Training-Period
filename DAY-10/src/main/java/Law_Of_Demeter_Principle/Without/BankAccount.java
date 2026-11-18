package Law_Of_Demeter_Principle.Without;


public class BankAccount {

    private final Transaction transaction;

    public BankAccount(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
