package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;

public class SalaryAccount extends BankAccount {

    public SalaryAccount(String accountNumber, String accountHolderName, BigDecimal balance) {
        super(accountNumber, accountHolderName, balance);
    }
}
