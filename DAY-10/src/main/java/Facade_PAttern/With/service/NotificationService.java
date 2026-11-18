package Facade_Pattern.With.service;


import Facade_Pattern.With.model.Account;

public class NotificationService {

    public void sendSuccessNotification(Account sender, Account receiver, double amount, String transactionId) {
        System.out.println("Sending transaction notifications...");
        System.out.println("[Notification -> " + sender.getAccountHolderName() + "]: Rs. " + (long) amount +
                " debited from " + sender.getAccountNumber() + " (Ref: " + transactionId + ")");
        System.out.println("[Notification -> " + receiver.getAccountHolderName() + "]: Rs. " + (long) amount +
                " credited to " + receiver.getAccountNumber() + " (Ref: " + transactionId + ")");
    }

    public void sendFailureNotification(Account sender, double amount, String reason) {
        String senderName = (sender != null) ? sender.getAccountHolderName() : "Customer";
        System.out.println("[Failure Notification -> " + senderName + "]: Transfer of Rs. " + (long) amount +
                " failed. Reason: " + reason);
    }
}
