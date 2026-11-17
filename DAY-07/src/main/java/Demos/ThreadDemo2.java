package Demos;

public class ThreadDemo2 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            try {

                while (true) {

                    System.out.println("Working");

                    Thread.sleep(1000);

                }

            } catch (InterruptedException e) {

                System.out.println("Thread interrupted");

            }

        });

        t1.start();
        // if we dont interrput the thread as the while condition is true it keeps on printing
        t1.interrupt();
    }
}
