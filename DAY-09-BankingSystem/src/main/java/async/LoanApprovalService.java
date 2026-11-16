package async;



import model.BankAccount;

import java.util.concurrent.*;

public class LoanApprovalService {
    private final ExecutorService executor;

    public LoanApprovalService(ExecutorService executor) {
        this.executor = executor;
    }

    // Callable + Future
    public Future<Boolean> runFraudCheckAsync(int accountId, double amount) {
        Callable<Boolean> fraudTask = () -> {
            Thread.sleep(100);
            return amount <= 50_000;
        };
        return executor.submit(fraudTask);
    }

    // CompletableFuture pipeline with async composition, timeout, and fallback
    public CompletableFuture<String> evaluateLoanApplication(BankAccount account, double requestedLoan) {
        CompletableFuture<Integer> creditScoreTask = CompletableFuture.supplyAsync(() -> {
            sleep(120);
            return 760;
        }, executor);

        CompletableFuture<Double> balanceTask = CompletableFuture.supplyAsync(() -> {
            sleep(80);
            return account.getBalance();
        }, executor);

        return creditScoreTask
                .thenCombine(balanceTask, (score, balance) -> {
                    if (score >= 700 && balance >= requestedLoan * 0.20) {
                        return "APPROVED: $" + requestedLoan + " loan for " + account.getOwner();
                    }
                    return "DENIED: Criteria not met.";
                })
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(ex -> "ERROR: Loan check failed - " + ex.getMessage());
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
