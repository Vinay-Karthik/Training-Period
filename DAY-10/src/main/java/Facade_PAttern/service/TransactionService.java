package Facade_PAttern.service;


import Facade_PAttern.model.Account;
import Facade_PAttern.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionService {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(10000);
    private final List<Transaction> transactions = new ArrayList<>();

    public String generateTransactionId() {
        System.out.println("Generating transaction ID...");
        return "TXN" + ID_GENERATOR.incrementAndGet();
    }

    public Transaction recordTransaction(String transactionId, Account sender, Account receiver, double amount, String status) {
        Transaction transaction = new Transaction(transactionId, sender, receiver, amount, status);
        transactions.add(transaction);
        if ("SUCCESS".equals(status)) {
            System.out.println("Transaction recorded successfully.");
        }
        return transaction;
    }
}
