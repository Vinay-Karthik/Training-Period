package Chain_Of_Responsibility_Pattern.Without;

public class Bank {

    public void process(Request request) {
        System.out.println("\nProcessing: " + request);

        if (request.getAmount() <= 10000) {
            System.out.println("Approved by Teller (limit: Rs.10,000)");
        } else if (request.getAmount() <= 100000) {
            System.out.println("Approved by Branch Manager (limit: Rs.1,00,000)");
        } else if (request.getAmount() <= 1000000) {
            System.out.println("Approved by Regional Manager (limit: Rs.10,00,000)");
        } else {
            System.out.println("Approved by Head Office (no limit)");
        }
    }
}
