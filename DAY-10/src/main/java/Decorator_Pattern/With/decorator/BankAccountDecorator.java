package Decorator_Pattern.With.decorator;


import Decorator_Pattern.With.interfaces.BankAccount;

public abstract class BankAccountDecorator implements BankAccount {

    protected final BankAccount bankAccount;

    public BankAccountDecorator(BankAccount wrappedAccount) {
        if (wrappedAccount == null) {
            throw new IllegalArgumentException("Wrapped BankAccount cannot be null.");
        }
        this.bankAccount = wrappedAccount;
    }

    @Override
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }

    @Override
    public double getBalance() {
        return bankAccount.getBalance();
    }

    @Override
    public String getDescription() {
        return bankAccount.getDescription();
    }
}
