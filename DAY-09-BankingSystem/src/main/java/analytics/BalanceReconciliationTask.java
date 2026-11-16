package analytics;


import model.BankAccount;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class BalanceReconciliationTask extends RecursiveTask<Double> {
    private static final int THRESHOLD = 500;
    private final List<BankAccount> accounts;
    private final int start;
    private final int end;

    public BalanceReconciliationTask(List<BankAccount> accounts, int start, int end) {
        this.accounts = accounts;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                sum += accounts.get(i).getBalance();
            }
            return sum;
        }

        int mid = start + (length / 2);
        BalanceReconciliationTask leftTask = new BalanceReconciliationTask(accounts, start, mid);
        BalanceReconciliationTask rightTask = new BalanceReconciliationTask(accounts, mid, end);

        leftTask.fork();
        double rightResult = rightTask.compute();
        double leftResult = leftTask.join();
        return leftResult + rightResult;
    }
}
