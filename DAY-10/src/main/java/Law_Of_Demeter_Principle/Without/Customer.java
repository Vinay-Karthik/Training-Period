package Law_Of_Demeter_Principle.Without;


public class Customer {

    private final String name;
    private final BankAccount account;

    public Customer(String name, BankAccount account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public BankAccount getAccount() {
        return account;
    }
}

