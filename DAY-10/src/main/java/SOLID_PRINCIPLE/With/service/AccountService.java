package SOLID_Principle.With.service;

import SOLID_Principle.With.model.Implementations.BankAccount;
import SOLID_Principle.With.model.Implementations.SalaryAccount;
import SOLID_Principle.With.model.Implementations.SavingsAccount;

import java.math.BigDecimal;

// OCP: new account types are added here without touching BankService
public class AccountService {
    public BankAccount create(String type, String accNo, String name, BigDecimal initialDeposit) {
        return switch (type.toUpperCase()) {
            case "SAVINGS" -> new SavingsAccount(accNo, name, initialDeposit);
            case "SALARY" -> new SalaryAccount(accNo, name, initialDeposit);
            default -> throw new IllegalArgumentException("Unsupported account type: " + type);
        };
    }
}
