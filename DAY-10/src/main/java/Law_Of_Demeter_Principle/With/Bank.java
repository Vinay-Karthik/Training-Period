package Law_Of_Demeter_Principle.With;


public class Bank {

    private final Customer customer;

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

