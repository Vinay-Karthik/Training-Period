package model;


import java.util.concurrent.atomic.AtomicLong;

public class Transaction {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1000);

    private final long txnId;
    private final String type;
    private final int fromAccountId;
    private final int toAccountId;
    private final double amount;

    public Transaction(String type, int fromAccountId, int toAccountId, double amount) {
        this.txnId = ID_GENERATOR.incrementAndGet();
        this.type = type;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("[TXN-%d | %s | From:%d -> To:%d | $%.2f]",
                txnId, type, fromAccountId, toAccountId, amount);
    }
}
