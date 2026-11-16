package Decorator_Pattern.interfaces;


public interface BankAccount {
    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();

    String getDescription();
}
