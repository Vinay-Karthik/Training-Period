package service;

import model.PayrollResult;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayrollReportService {

    public record ReportSummary(
        double totalGross,
        double totalTax,
        double totalDeductions,
        double totalNet
    ) {}

    public CompletableFuture<ReportSummary> generateSummaryAsync(Collection<PayrollResult> results) {
        ExecutorService reportExecutor = Executors.newFixedThreadPool(2);

        CompletableFuture<Double> grossFuture = CompletableFuture.supplyAsync(() ->
            results.stream()
                .filter(PayrollResult::success)
                .mapToDouble(PayrollResult::grossSalary)
                .sum(), reportExecutor);

        CompletableFuture<Double> taxFuture = CompletableFuture.supplyAsync(() ->
            results.stream()
                .filter(PayrollResult::success)
                .mapToDouble(PayrollResult::taxDeduction)
                .sum(), reportExecutor);

        CompletableFuture<Double> deductionsFuture = CompletableFuture.supplyAsync(() ->
            results.stream()
                .filter(PayrollResult::success)
                .mapToDouble(PayrollResult::otherDeductions)
                .sum(), reportExecutor);

        CompletableFuture<Double> netFuture = CompletableFuture.supplyAsync(() ->
            results.stream()
                .filter(PayrollResult::success)
                .mapToDouble(PayrollResult::netSalary)
                .sum(), reportExecutor);

        return CompletableFuture.allOf(grossFuture, taxFuture, deductionsFuture, netFuture)
            .thenApplyAsync(v -> new ReportSummary(
                roundToCents(grossFuture.join()),
                roundToCents(taxFuture.join()),
                roundToCents(deductionsFuture.join()),
                roundToCents(netFuture.join())
            ), reportExecutor)
            .whenComplete((res, ex) -> reportExecutor.shutdown());
    }

    private static double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
