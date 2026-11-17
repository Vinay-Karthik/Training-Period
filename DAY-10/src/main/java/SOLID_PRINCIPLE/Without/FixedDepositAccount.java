package SOLID_PRINCIPLE.Without;


import java.math.BigDecimal;

public class FixedDepositAccount extends BankAccount {
    public FixedDepositAccount(String accountNumber, String holderName, BigDecimal balance) {
        super(accountNumber, holderName, balance);
    }
}
