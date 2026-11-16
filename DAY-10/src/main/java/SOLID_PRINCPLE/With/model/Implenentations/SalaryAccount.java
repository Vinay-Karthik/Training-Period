package SOLID_PRINCPLE.With.model.Implenentations;

import java.math.BigDecimal;

public class SalaryAccount extends BankAccount {
    public SalaryAccount(String accountNumber, String holderName, BigDecimal balance) {
        super(accountNumber, holderName, balance);
    }
}
