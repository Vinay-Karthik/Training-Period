package Singleton_Pattern.With;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread-Safe Singleton (With Singleton Pattern) ===");

        Thread thread1 = new Thread(() -> {
            ThreadSafeSingleton s1 = ThreadSafeSingleton.getInstance();
            System.out.println("Thread 1: " + s1 + " (Hash: " + s1.hashCode() + ")");
        });

        Thread thread2 = new Thread(() -> {
            ThreadSafeSingleton s2 = ThreadSafeSingleton.getInstance();
            System.out.println("Thread 2: " + s2 + " (Hash: " + s2.hashCode() + ")");
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("\n=== Double-Checked Locking Singleton ===");
        DoubleCheckedSingleton d1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton d2 = DoubleCheckedSingleton.getInstance();
        System.out.println("Instance 1 Hash: " + d1.hashCode());
        System.out.println("Instance 2 Hash: " + d2.hashCode());
        System.out.println("Are both instances the same? " + (d1 == d2));
    }
}
