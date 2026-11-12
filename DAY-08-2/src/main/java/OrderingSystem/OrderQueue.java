package OrderingSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue {

    private final Queue<String> orders = new LinkedList<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition orderAvailable = lock.newCondition();

    // Customer places an order
    public void placeOrder(String order) {
        lock.lock();
        try {
            orders.add(order);
            System.out.println("Customer placed: " + order);
            // Notify the waiting chef
            orderAvailable.signal();

//            we use this if we have multiple threads waiting to access
//            orderAvailable.signalAll();


        } finally {
            lock.unlock();
        }
    }

    // Chef takes an order
    public String takeOrder() {
        lock.lock();
        try {
            // Wait while there are no orders
            while (orders.isEmpty()) {
                System.out.println("Chef is waiting for orders...");
                orderAvailable.await();
            }
            String order = orders.poll();
            System.out.println("Chef preparing: " + order);
            return order;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
}

