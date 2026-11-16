package core;


import model.BankAccount;
import model.Transaction;
import util.RequestContext;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class BankService {
    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final BlockingQueue<Transaction> ledgerQueue = new LinkedBlockingQueue<>(100);

    // volatile guarantees all threads immediately see maintenance toggle changes
    private volatile boolean bankOpen = true;

    public void registerAccount(BankAccount account) {
        accounts.put(account.getAccountId(), account);
    }

    public BankAccount getAccount(int id) {
        return accounts.get(id);
    }

    public Collection<BankAccount> getAllAccounts() {
        return accounts.values();
    }

    public BlockingQueue<Transaction> getLedgerQueue() {
        return ledgerQueue;
    }

    public void setBankOpen(boolean status) {
        this.bankOpen = status;
        System.out.println("\n[ADMIN] Bank Open Status set to: " + status);
    }

    public boolean transfer(int fromId, int toId, double amount) {
        if (!bankOpen) {
            System.out.println("[" + RequestContext.get() + "] Transfer rejected: Bank closed.");
            return false;
        }

        BankAccount from = accounts.get(fromId);
        BankAccount to = accounts.get(toId);
        if (from == null || to == null || fromId == toId) {
            return false;
        }

        // Strict lock ordering by accountId prevents circular-wait deadlocks
        BankAccount firstLock = (from.getAccountId() < to.getAccountId()) ? from : to;
        BankAccount secondLock = (from.getAccountId() < to.getAccountId()) ? to : from;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    // Producer places receipt into queue
                    ledgerQueue.offer(new Transaction("TRANSFER", fromId, toId, amount));
                    return true;
                }
                return false;
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }
}
