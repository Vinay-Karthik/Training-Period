package Employee_System;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int totalEmployees = 1_000;
        List<Integer> employeeIds = IntStream.rangeClosed(1, totalEmployees).boxed().toList();
        IntegrationService service = new IntegrationService();

        System.out.println("Processing " + totalEmployees + " employees (100ms I/O per record)...\n");

        // 1. Run Traditional Thread Pool (50 OS threads)
        runAndMeasure("Traditional Thread Pool (50 threads)", () ->
                service.runWithThreadPool(employeeIds, 50)
        );

        // 2. Run Virtual Threads
        runAndMeasure("Virtual Threads (1 per task)", () ->
                service.runWithVirtualThreads(employeeIds)
        );
    }

    private static void runAndMeasure(String label, Runnable task) {
        System.gc();
        long memBefore = getUsedMemoryMB();
        long startTime = System.currentTimeMillis();

        // Start a lightweight watcher to capture peak OS threads during execution
        int[] peakThreads = {0};
        Thread watcher = Thread.ofPlatform().daemon().start(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                int current = ManagementFactory.getThreadMXBean().getThreadCount();
                peakThreads[0] = Math.max(peakThreads[0], current);
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e) { break; }
            }
        });

        task.run(); // Execute the batch

        watcher.interrupt();
        long elapsedMs = System.currentTimeMillis() - startTime;
        long memAfter = getUsedMemoryMB();
        double throughput = (1_000.0 / elapsedMs) * 1000;

        System.out.println("=== " + label + " ===");
        System.out.printf("Total Time      : %d ms%n", elapsedMs);
        System.out.printf("Throughput      : %.1f records/sec%n", throughput);
        System.out.printf("Peak OS Threads : %d%n", peakThreads[0]);
        System.out.printf("Memory Delta    : ~%d MB%n%n", Math.max(0, memAfter - memBefore));
    }

    private static long getUsedMemoryMB() {
        Runtime rt = Runtime.getRuntime();
        return (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
    }
}
