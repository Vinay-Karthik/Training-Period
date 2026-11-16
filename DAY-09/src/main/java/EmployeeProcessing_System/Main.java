package EmployeeProcessing_System;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int employeeCount = args.length > 0 ? Integer.parseInt(args[0]) : 2000;
        int apiDelayMs = args.length > 1 ? Integer.parseInt(args[1]) : 20;
        int poolSize = args.length > 2
                ? Integer.parseInt(args[2])
                : Math.max(4, Runtime.getRuntime().availableProcessors() * 2);

        if (employeeCount < 1 || apiDelayMs < 0 || poolSize < 1) {
            throw new IllegalArgumentException(
                    "Employee count and pool size must be positive; delay cannot be negative."
            );
        }

        List<Employee> employees = EmployeeDataGenerator.generate(employeeCount);
        BenchmarkRunner runner = new BenchmarkRunner();

        System.out.println("\nEMPLOYEE DATA INTEGRATION BENCHMARK");
        System.out.println("Employees: " + employeeCount);
        System.out.println("Simulated API delay: " + apiDelayMs + " ms");
        System.out.println("Thread pool size: " + poolSize);
        System.out.println();

        // Warm-up helps reduce some JVM startup/JIT effects.
        System.out.println("Running a small warm-up...");
        List<Employee> warmup = EmployeeDataGenerator.generate(Math.min(100, employeeCount));
        runner.runThreadPool(warmup, poolSize, apiDelayMs);
        runner.runVirtualThreads(warmup, apiDelayMs);

        System.out.println("\nRunning full benchmark...");
        BenchmarkResult poolResult =
                runner.runThreadPool(employees, poolSize, apiDelayMs);
        BenchmarkResult virtualResult =
                runner.runVirtualThreads(employees, apiDelayMs);

        System.out.println("\nRESULTS");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        poolResult.print();
        virtualResult.print();

        if (poolResult.employeesPerSecond() > 0) {
            double improvement =
                    (virtualResult.employeesPerSecond() / poolResult.employeesPerSecond() - 1) * 100;
            System.out.printf("%nVirtual-thread throughput change vs pool: %.2f%%%n", improvement);
        }

        System.out.println("\nNote: heap usage is a rough before/after snapshot, not peak memory.");
        System.out.println("Peak JVM thread count includes JVM/runtime threads, not just task threads.");
    }
}
