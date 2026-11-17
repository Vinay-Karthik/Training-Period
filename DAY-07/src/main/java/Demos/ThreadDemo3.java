package Demos;

public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
//                counter1.increment();
                Counter.increment();
            }

        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
//                counter1.increment();
                Counter.increment();
            }
        });
        t1.start();
        t2.start();

        // if we dont use join main threads gets executed first and program exits printing 0
        t1.join();
        t2.join();
        System.out.println(Counter.count);
    }
}

class Counter {

    static int count = 0;

    public static synchronized void increment() {
//        synchronized (this) {
        count++;

    }
}
