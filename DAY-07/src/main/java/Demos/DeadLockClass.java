package Demos;

public class DeadLockClass {

    static Object lock1 = new Object();

    static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (lock1) {

                System.out.println("Thread 1 acquired lock1");

                synchronized (lock2) {

                    System.out.println("Thread 1 acquired lock2");

                }

            }

        });

        Thread t2 = new Thread(() -> {

            synchronized (lock2) {

                System.out.println("Thread 2 acquired lock2");

                synchronized (lock1) {

                    System.out.println("Thread 2 acquired lock1");

                }

            }

        });

        t1.start();
        t2.start();
    }
}
