package Law_Of_Demeter_Principle.With;


public class Customer {

    private final String name;
    private final BankAccount account;

    public Customer(String name, BankAccount account) {
        this.name = name;
        this.account = account;
    }

    public double getTransactionAmount() {
        return account.getTransactionAmount();
    }

    public String getTransactionType() {
        return account.getTransactionType();
    }

    public String getName() {
        return name;
    }
}