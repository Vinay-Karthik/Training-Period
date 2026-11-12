package service;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationPreventionDemo {
    // A fair lock generally grants access in FIFO order to waiting threads.
    private final ReentrantLock fairLock = new ReentrantLock(true);

    public void runDemo() throws InterruptedException {
        Runnable worker = () -> {
            fairLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the fair lock");
            } finally {
                fairLock.unlock();
            }
        };

        Thread first = new Thread(worker, "worker-1");
        Thread second = new Thread(worker, "worker-2");
        Thread third = new Thread(worker, "worker-3");
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        System.out.println("Fair lock reduces lock-acquisition starvation; it cannot guarantee CPU scheduling fairness.");
    }
}
