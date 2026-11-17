package SOLID_PRINCIPLE.Without;

import java.math.BigDecimal;

// ISP VIOLATION: Fat interface forcing all account types to implement unused operations.
public interface BankOperations {
    void deposit(BigDecimal amount, String account_No);

    void withdraw(BigDecimal amount, String account_No);

}
