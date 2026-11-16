package gateway;


import model.BankAccount;

import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class CustomerDashboardService {

    public String loadCustomerDashboard(BankAccount account) throws Exception {
        // StructuredTaskScope ensures all subtasks finish or cancel together—no leaked threads
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<Double> checkingSubtask = scope.fork(() -> {
                Thread.sleep(80);
                return account.getBalance();
            });

            Supplier<String> creditCardSubtask = scope.fork(() -> {
                Thread.sleep(100);
                return "Gold Card ($250 due)";
            });

            Supplier<String> loanStatusSubtask = scope.fork(() -> {
                Thread.sleep(90);
                return "Auto Loan (Active)";
            });

            scope.join();           // Wait for all subtasks
            scope.throwIfFailed();  // Propagate exception if any single service failed

            return String.format("Dashboard for %s -> Checking: $%.2f | Card: %s | Loan: %s",
                    account.getOwner(),
                    checkingSubtask.get(),
                    creditCardSubtask.get(),
                    loanStatusSubtask.get());
        }
    }
}
