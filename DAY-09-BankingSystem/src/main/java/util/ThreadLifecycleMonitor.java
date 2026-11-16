package util;

public class ThreadLifecycleMonitor implements Runnable {
    private final Thread targetThread;

    public ThreadLifecycleMonitor(Thread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {
        System.out.println("[Monitor] Observing thread: " + targetThread.getName()
                + " | Current State: " + targetThread.getState());
    }
}
