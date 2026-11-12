package service;

import java.util.concurrent.CountDownLatch;

public class DeadlockDemo {
    public static void runDemo() throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        CountDownLatch bothHoldFirstLock = new CountDownLatch(2);

        Thread first = new Thread(() -> acquireInOppositeOrder(lockA, lockB, bothHoldFirstLock), "deadlock-A-then-B");
        Thread second = new Thread(() -> acquireInOppositeOrder(lockB, lockA, bothHoldFirstLock), "deadlock-B-then-A");
        // Daemon threads ensure this intentionally deadlocked demo cannot keep the JVM alive.
        first.setDaemon(true);
        second.setDaemon(true);
        first.start();
        second.start();

        first.join(500);
        second.join(500);
        System.out.println("Deadlock demo thread states: " + first.getState() + ", " + second.getState());
        System.out.println("Prevention: always acquire shared locks in the same global order.");
    }

    private static void acquireInOppositeOrder(Object firstLock, Object secondLock, CountDownLatch latch) {
        synchronized (firstLock) {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (secondLock) {
                System.out.println("Both locks acquired");
            }
        }
    }
}
