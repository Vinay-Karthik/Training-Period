package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionDemo {
    private int unsafeCounter = 0;
    private int synchronizedCounter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    public void runDemo(final int totalIncrements) throws InterruptedException {
        unsafeCounter = 0;
        synchronizedCounter = 0;
        atomicCounter.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < totalIncrements; i++) {
            executor.submit(() -> {
                unsafeCounter++;
                incrementSynchronized();
                atomicCounter.incrementAndGet();
            });
        }

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            throw new IllegalStateException("Race-condition demo did not finish within 10 seconds");
        }

        System.out.println("\n--- Race Condition Demonstration (" + totalIncrements + " Increments across 10 Threads) ---");
        System.out.println("Unsafe Counter Result (Lost Updates): " + unsafeCounter);
        System.out.println("Synchronized Counter Result:          " + synchronizedCounter);
        System.out.println("AtomicInteger Counter Result:         " + atomicCounter.get());
    }

    private synchronized void incrementSynchronized() {
        synchronizedCounter++;
    }

    public int getUnsafeCounter() { return unsafeCounter; }
    public int getSynchronizedCounter() { return synchronizedCounter; }
    public int getAtomicCounter() { return atomicCounter.get(); }
}
