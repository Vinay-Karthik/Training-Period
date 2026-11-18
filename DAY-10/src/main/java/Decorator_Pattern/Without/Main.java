package Decorator_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bank Account (Without Decorator Pattern) ===");

        System.out.println("\n--- Basic Account (No Alerts) ---");
        BankAccount basic = new BankAccount(10000, false, false, false);
        basic.deposit(1500);

        System.out.println("\n--- Account with SMS Alert ---");
        BankAccount smsAccount = new BankAccount(10000, false, true, false);
        smsAccount.deposit(1500);

        System.out.println("\n--- Account with SMS and Email Alerts ---");
        BankAccount multiAlertAccount = new BankAccount(10000, true, true, false);
        multiAlertAccount.withdraw(500);
    }
}
