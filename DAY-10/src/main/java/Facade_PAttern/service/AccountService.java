package Facade_Pattern.service;


import Facade_Pattern.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accountRegistry = new HashMap<>();

    public void registerAccount(Account account) {
        if (account != null && account.getAccountNumber() != null) {
            accountRegistry.put(account.getAccountNumber(), account);
        }
    }

    public Account getAccountDetails(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid account number: null or empty.");
        }
        Account account = accountRegistry.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account does not exist: " + accountNumber);
        }
        return account;
    }

    public Account validateAccount(Account account) {
        if (account == null || account.getAccountNumber() == null || account.getAccountNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid account details provided.");
        }
        // Check registry if accounts were registered; otherwise validate the passed object
        Account target = accountRegistry.isEmpty() ?
                account : getAccountDetails(account.getAccountNumber());

        if (!target.isActive()) {
            throw new IllegalStateException("Account is inactive: " + target.getAccountNumber());
        }
        return target;
    }
}
