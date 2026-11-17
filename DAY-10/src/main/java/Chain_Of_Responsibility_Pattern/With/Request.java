package Chain_Of_Responsibility_Pattern.With;

public class Request {

    private final double amount;
    private final String type;
    private final String description;

    public Request(double amount, String type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return type + " of Rs." + amount + " - " + description;
    }
}
