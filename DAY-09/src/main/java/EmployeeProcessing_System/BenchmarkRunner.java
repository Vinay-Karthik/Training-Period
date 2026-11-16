package EmployeeProcessing_System;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkRunner {
    private final EmployeeIntegrationService integrationService =
            new EmployeeIntegrationService();

    public BenchmarkResult runThreadPool(List<Employee> employees, int poolSize, int delayMs)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        return run("Traditional pool", employees, delayMs, executor);
    }

    public BenchmarkResult runVirtualThreads(List<Employee> employees, int delayMs)
            throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        return run("Virtual threads", employees, delayMs, executor);
    }

    private BenchmarkResult run(
            String name,
            List<Employee> employees,
            int delayMs,
            ExecutorService executor
    ) throws InterruptedException {
        AtomicInteger completed = new AtomicInteger();
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        threadBean.resetPeakThreadCount();

        long heapBefore = usedHeapBytes();
        long start = System.nanoTime();

        try {
            for (Employee employee : employees) {
                executor.submit(() -> {
                    integrationService.integrate(employee, delayMs);
                    completed.incrementAndGet();
                });
            }
        } finally {
            executor.shutdown();
        }

        boolean finished = executor.awaitTermination(10, TimeUnit.MINUTES);
        long end = System.nanoTime();

        if (!finished) {
            executor.shutdownNow();
            throw new IllegalStateException(name + " did not finish within 10 minutes");
        }

        long heapAfter = usedHeapBytes();
        double seconds = (end - start) / 1_000_000_000.0;
        double throughput = completed.get() / seconds;
        long heapUsedMb = Math.max(0, heapAfter - heapBefore) / (1024 * 1024);

        return new BenchmarkResult(
                name,
                completed.get(),
                seconds,
                throughput,
                threadBean.getPeakThreadCount(),
                heapUsedMb
        );
    }

    private long usedHeapBytes() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
