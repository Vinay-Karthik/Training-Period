package model;

public sealed interface Account permits SavingsAccount, CurrentAccount {

    BankAccount getAccountDetails();

    AccountType getAccountType();

    void displayAccountInformation();
}
