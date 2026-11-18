package Decorator_Pattern.With.interfaces;


public interface BankAccount {
    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();

    String getDescription();
}
