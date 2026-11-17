package SOLID_PRINCIPLE.With.repository.Implementations;

import SOLID_PRINCIPLE.With.model.Implementations.BankAccount;
import SOLID_PRINCIPLE.With.repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    @Override
    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public void save(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Map<String, BankAccount> findAll() {
        return new HashMap<>(accounts);
    }
}
