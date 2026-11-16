package SOLID_PRINCPLE.With.repository;

import SOLID_PRINCPLE.With.model.Implenentations.BankAccount;

import java.util.Map;
import java.util.Optional;

// DIP: storage behind an interface so it can be swapped (DB, file, etc.)
public interface AccountRepository {
    Optional<BankAccount> findByAccountNumber(String accountNumber);

    void save(BankAccount account);

    Map<String, BankAccount> findAll();
}
