package Facade_PAttern.model;


import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final Account sender;
    private final Account receiver;
    private final double amount;
    private final LocalDateTime timestamp;
    private String status;

    public Transaction(String transactionId, Account sender, Account receiver, double amount, String status) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
