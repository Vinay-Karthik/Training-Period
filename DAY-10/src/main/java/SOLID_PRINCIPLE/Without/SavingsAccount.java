package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, String holderName, BigDecimal balance) {
        super(accountNumber, holderName, balance);
    }
}