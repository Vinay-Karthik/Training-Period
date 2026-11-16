package Law_of_Demeter;


public class Main {

    public static void main(String[] args) {

        TransactionDetails details = new TransactionDetails(5000, "DEPOSIT");

        Transaction transaction = new Transaction(details);

        BankAccount account = new BankAccount(transaction);

        Customer customer = new Customer("Rahul", account);

        Bank bank = new Bank(customer);

        // Violating Law of Demeter

        double amount = bank.getCustomer()
                .getAccount()
                .getTransaction()
                .getDetails()
                .getAmount();

        String type = bank.getCustomer()
                .getAccount()
                .getTransaction()
                .getDetails()
                .getTransactionData();

        System.out.println("Transaction Amount: " + amount);
        System.out.println("Transaction Type: " + type);
    }
}
