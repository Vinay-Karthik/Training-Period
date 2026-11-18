package Law_Of_Demeter_Principle.Without;


public class Bank {

    private final Customer customer;

    public Bank(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

