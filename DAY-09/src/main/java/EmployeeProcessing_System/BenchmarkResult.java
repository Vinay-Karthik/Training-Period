package EmployeeProcessing_System;

public record BenchmarkResult(
        String implementation,
        int employeesProcessed,
        double elapsedSeconds,
        double employeesPerSecond,
        int peakJvmThreadCount,
        long approximateHeapUsedMb
) {
    public void print() {
        System.out.printf(
                "%-20s | Employees: %6d | Time: %8.2f s | Throughput: %10.2f employees/s | Peak JVM threads: %4d | Heap used after run: %d MB%n",
                implementation,
                employeesProcessed,
                elapsedSeconds,
                employeesPerSecond,
                peakJvmThreadCount,
                approximateHeapUsedMb
        );
    }
}
