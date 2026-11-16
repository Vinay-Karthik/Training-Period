package gateway;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentGatewayBenchmark {

    public static void runComparison(int requestCount) {
        System.out.println("\n=== VIRTUAL THREADS VS PLATFORM THREADS (" + requestCount + " I/O Requests) ===");

        long platformStart = System.currentTimeMillis();
        try (ExecutorService platformPool = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < requestCount; i++) {
                platformPool.submit(() -> simulateNetworkCall(50));
            }
        }
        long platformTime = System.currentTimeMillis() - platformStart;

        long virtualStart = System.currentTimeMillis();
        try (ExecutorService virtualPool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < requestCount; i++) {
                virtualPool.submit(() -> simulateNetworkCall(50));
            }
        }
        long virtualTime = System.currentTimeMillis() - virtualStart;

        System.out.println("Platform Thread Pool (100 threads) : " + platformTime + " ms");
        System.out.println("Java 21 Virtual Threads            : " + virtualTime + " ms");
    }

    private static void simulateNetworkCall(long ms) {
        try {
            Thread.sleep(ms); // Virtual thread unmounts carrier thread here
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
