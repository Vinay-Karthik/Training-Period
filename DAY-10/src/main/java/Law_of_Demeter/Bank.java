package Law_of_Demeter;


public class Bank {

    private Customer customer;

    public Bank(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

