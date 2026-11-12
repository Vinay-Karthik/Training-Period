package service;

public class VolatileDemo {
    private volatile boolean running = true;

    public void runDemo() throws InterruptedException {
        running = true;
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            while (running) {
                // Work loop: volatile ensures this thread observes the updated flag.
                Thread.onSpinWait();
            }
            System.out.println("Worker observed running=false and stopped");
        }, "volatile-worker");

        worker.start();
        Thread.sleep(100); // Demo coordination only; not used for memory visibility.
        System.out.println("Main thread requests worker shutdown");
        running = false;
        worker.join(2_000);
        if (worker.isAlive()) {
            throw new IllegalStateException("Worker did not stop in time");
        }
    }

}
