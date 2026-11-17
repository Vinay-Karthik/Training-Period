package Facade_Pattern.service;


import Facade_Pattern.model.Transaction;

public class ReceiptService {

    public void generateReceipt(Transaction transaction) {
        System.out.println("\n===== TRANSACTION RECEIPT =====\n");
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Sender: " + transaction.getSender().getAccountHolderName() +
                " (" + transaction.getSender().getAccountNumber() + ")");
        System.out.println("Receiver: " + transaction.getReceiver().getAccountHolderName() +
                " (" + transaction.getReceiver().getAccountNumber() + ")");
        System.out.println("Amount: Rs. " + (long) transaction.getAmount());
        System.out.println("Status: " + transaction.getStatus());
    }
}
