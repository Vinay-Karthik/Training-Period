
import analytics.BalanceReconciliationTask;
import async.LoanApprovalService;
import core.BankService;
import gateway.CustomerDashboardService;
import gateway.PaymentGatewayBenchmark;
import model.BankAccount;
import pipeline.LedgerConsumer;
import util.RequestContext;
import util.ThreadLifecycleMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BankService bank = new BankService();

        // 1. Thread Lifecycle & Producer-Consumer Setup
        LedgerConsumer ledgerConsumer = new LedgerConsumer(bank.getLedgerQueue());
        Thread ledgerThread = new Thread(ledgerConsumer, "Ledger-Consumer");
        ThreadLifecycleMonitor monitor = new ThreadLifecycleMonitor(ledgerThread);

        monitor.run(); // NEW
        ledgerThread.start();
        monitor.run(); // RUNNABLE

        // Register 2,000 accounts ($1,000 each = $2,000,000 total)
        for (int i = 1; i <= 2000; i++) {
            bank.registerAccount(new BankAccount(i, "Customer-" + i, 1000.00));
        }

        // 2. Fixed Thread Pool + Deadlock-Free Transfers + ThreadLocal
        ExecutorService atmPool = Executors.newFixedThreadPool(8);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            final int reqId = i;
            futures.add(atmPool.submit(() -> {
                RequestContext.set("REQ-" + reqId);
                try {
                    if (reqId % 2 == 0) bank.transfer(1, 2, 10.0);
                    else bank.transfer(2, 1, 10.0);
                } finally {
                    RequestContext.clear();
                }
            }));
        }

        for (Future<?> f : futures) f.get();
        Thread.sleep(100);

        monitor.run(); // WAITING (blocked on BlockingQueue.take)
        System.out.println("Transactions Logged : " + ledgerConsumer.getProcessedCount());
        System.out.println("Account 1 Balance   : $" + bank.getAccount(1).getBalance());
        System.out.println("Account 2 Balance   : $" + bank.getAccount(2).getBalance());

        // Test volatile flag
        bank.setBankOpen(false);
        bank.transfer(1, 2, 50.0);
        bank.setBankOpen(true);

        // 3. Callable/Future & CompletableFuture
        LoanApprovalService loanService = new LoanApprovalService(atmPool);
        System.out.println("\nFraud Check Passed  : " + loanService.runFraudCheckAsync(1, 15000).get());
        System.out.println("Loan Decision       : " + loanService.evaluateLoanApplication(bank.getAccount(1), 4000.0).get());

        // 4. ForkJoinPool End-of-Day Reconciliation
        List<BankAccount> allAccounts = new ArrayList<>(bank.getAllAccounts());
        double totalLiquidity = ForkJoinPool.commonPool()
                .invoke(new BalanceReconciliationTask(allAccounts, 0, allAccounts.size()));
        System.out.printf("Total Bank Liquidity: $%,.2f%n", totalLiquidity);

        // 5. Structured Concurrency Dashboard (Java 21+)
        CustomerDashboardService dashboardService = new CustomerDashboardService();
        System.out.println(dashboardService.loadCustomerDashboard(bank.getAccount(1)));

        // 6. Virtual Threads vs Platform Threads Benchmark
        PaymentGatewayBenchmark.runComparison(2000);

        // Graceful Shutdown
        atmPool.shutdown();
        ledgerThread.interrupt();
        ledgerThread.join();
        monitor.run(); // TERMINATED
    }
}
