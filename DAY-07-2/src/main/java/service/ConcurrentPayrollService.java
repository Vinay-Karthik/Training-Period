package service;

import model.Employee;
import model.PayrollResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentPayrollService {
    private final PayrollService payrollService;
    private final ConcurrentHashMap<String, PayrollResult> payrollResultsMap = new ConcurrentHashMap<>();

    private final AtomicLong totalGrossCents = new AtomicLong();
    private final AtomicLong totalTaxCents = new AtomicLong();
    private final AtomicLong totalDeductionsCents = new AtomicLong();
    private final AtomicLong totalNetCents = new AtomicLong();
    private final AtomicInteger successCount = new AtomicInteger();
    private final AtomicInteger failureCount = new AtomicInteger();

    private final ReentrantLock auditLock = new ReentrantLock();
    private int auditedRecordCount;

    public ConcurrentPayrollService(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    public List<PayrollResult> processBatch(List<Employee> employees, int threadPoolSize)
            throws InterruptedException {
        if (employees == null) throw new IllegalArgumentException("employees cannot be null");
        if (threadPoolSize <= 0) throw new IllegalArgumentException("threadPoolSize must be greater than zero");
        ensureUniqueEmployeeIds(employees);
        resetBatchState();

        try (ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize)) {
            List<Callable<PayrollResult>> tasks = employees.stream()
                    .<Callable<PayrollResult>>map(employee -> () -> processOneEmployee(employee))
                    .toList();

            List<PayrollResult> results = new ArrayList<>(tasks.size());
            for (Future<PayrollResult> future : executor.invokeAll(tasks)) {
                try {
                    results.add(future.get());
                } catch (ExecutionException e) {
                    // A task that unexpectedly throws still counts as a failed task.
                    failureCount.incrementAndGet();
                    Throwable cause = e.getCause();
                    System.err.println("Payroll task failed: " +
                            (cause == null ? e.getMessage() : cause.getMessage()));
                }
            }
            return List.copyOf(results);
        }
    }

    private PayrollResult processOneEmployee(Employee employee) {
        PayrollResult result;
        try {
            result = payrollService.calculatePayroll(employee);
        } catch (RuntimeException ex) {
            result = PayrollResult.failure(employee.id(), ex.getMessage());
        }

        PayrollResult previous = payrollResultsMap.putIfAbsent(employee.id(), result);
        if (previous != null) {
            throw new IllegalStateException("Duplicate payroll result for employee " + employee.id());
        }

        if (result.success()) {
            successCount.incrementAndGet();
            totalGrossCents.addAndGet(toCents(result.grossSalary()));
            totalTaxCents.addAndGet(toCents(result.taxDeduction()));
            totalDeductionsCents.addAndGet(toCents(result.otherDeductions()));
            totalNetCents.addAndGet(toCents(result.netSalary()));
            auditRecord(); // Runs on the worker thread, so the lock protects concurrent updates.
        } else {
            failureCount.incrementAndGet();
        }
        return result;
    }

    private void auditRecord() {
        auditLock.lock();
        try {
            auditedRecordCount++;
        } finally {
            auditLock.unlock();
        }
    }

    private void ensureUniqueEmployeeIds(List<Employee> employees) {
        Set<String> ids = new HashSet<>();
        for (Employee employee : employees) {
            if (employee == null) throw new IllegalArgumentException("Employee list cannot contain null");
            if (!ids.add(employee.id())) {
                throw new IllegalArgumentException("Duplicate employee ID in batch: " + employee.id());
            }
        }
    }

    private void resetBatchState() {
        payrollResultsMap.clear();
        totalGrossCents.set(0);
        totalTaxCents.set(0);
        totalDeductionsCents.set(0);
        totalNetCents.set(0);
        successCount.set(0);
        failureCount.set(0);
        auditLock.lock();
        try {
            auditedRecordCount = 0;
        } finally {
            auditLock.unlock();
        }
    }

    private long toCents(double amount) {
        return Math.round(amount * 100.0);
    }

    public Map<String, PayrollResult> getPayrollResultsMap() {
        return Map.copyOf(payrollResultsMap);
    }
    public long getTotalGrossCents() { return totalGrossCents.get(); }
    public long getTotalTaxCents() { return totalTaxCents.get(); }
    public long getTotalDeductionsCents() { return totalDeductionsCents.get(); }
    public long getTotalNetCents() { return totalNetCents.get(); }
    public int getSuccessCount() { return successCount.get(); }
    public int getFailureCount() { return failureCount.get(); }
    public int getAuditedRecordCount() {
        auditLock.lock();
        try { return auditedRecordCount; }
        finally { auditLock.unlock(); }
    }
}
