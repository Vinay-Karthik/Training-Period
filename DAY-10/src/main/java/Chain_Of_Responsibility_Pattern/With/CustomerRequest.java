package Chain_Of_Responsibility_Pattern.With;

public record CustomerRequest(double amount, String type, String description) {

    @Override
    public String toString() {
        return type + " of Rs." + amount + " - " + description;
    }
}
