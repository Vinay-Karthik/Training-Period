package Law_of_Demeter.With;


public class Bank {

    private Customer customer;

    public Bank(Customer customer) {
        this.customer = customer;
    }

    public double getCustomerTransactionAmount() {
        return customer.getTransactionAmount();
    }

    public String getCustomerTransactionType() {
        return customer.getTransactionType();
    }
}

