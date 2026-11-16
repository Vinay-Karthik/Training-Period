package Law_of_Demeter.With;



public class Customer {

    private String name;
    private BankAccount account;

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