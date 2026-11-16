package model;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int accountId;
    private final String owner;
    private double balance;

    // Fair lock (true) grants access in FIFO order to prevent thread starvation
    private final ReentrantLock lock = new ReentrantLock(true);

    public BankAccount(int accountId, String owner, double initialBalance) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getOwner() {
        return owner;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            if (amount > 0) {
                balance += amount;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    if (balance >= amount) {
                        balance -= amount;
                        return true;
                    }
                    return false;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}
