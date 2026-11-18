package Decorator_Pattern.Without;

// Without Decorator: Using boolean flags and conditionals inside a single class, violating Single Responsibility and Open/Closed principles
public class BankAccount {
    private double balance;
    private final boolean hasEmailAlert;
    private final boolean hasSmsAlert;
    private final boolean hasWhatsAppAlert;

    public BankAccount(double balance, boolean hasEmailAlert, boolean hasSmsAlert, boolean hasWhatsAppAlert) {
        this.balance = balance;
        this.hasEmailAlert = hasEmailAlert;
        this.hasSmsAlert = hasSmsAlert;
        this.hasWhatsAppAlert = hasWhatsAppAlert;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited Rs. " + amount + " into account.");
        sendNotifications("Deposit of Rs. " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn Rs. " + amount + " from account.");
            sendNotifications("Withdrawal of Rs. " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void sendNotifications(String transactionInfo) {
        if (hasSmsAlert) {
            System.out.println("[SMS Alert] " + transactionInfo + ". Current balance: Rs. " + balance);
        }
        if (hasEmailAlert) {
            System.out.println("[Email Alert] " + transactionInfo + ". Current balance: Rs. " + balance);
        }
        if (hasWhatsAppAlert) {
            System.out.println("[WhatsApp Alert] " + transactionInfo + ". Current balance: Rs. " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}
