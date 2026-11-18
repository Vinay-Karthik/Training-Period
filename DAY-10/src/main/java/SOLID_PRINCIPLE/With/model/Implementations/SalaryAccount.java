package SOLID_Principle.With.model.Implementations;

import java.math.BigDecimal;

public class SalaryAccount extends BankAccount {
    public SalaryAccount(String accountNumber, String holderName, BigDecimal balance) {
        super(accountNumber, holderName, balance);
    }
}
