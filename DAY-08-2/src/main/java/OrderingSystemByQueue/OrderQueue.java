package OrderingSystemByQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class OrderQueue {

    private final BlockingQueue<String> orders =
            new ArrayBlockingQueue<>(3);

    // Customer places an order
    public void placeOrder(String order) {

        try {

            System.out.println(
                    "Customer placing: " + order
            );

            orders.put(order);

            System.out.println(
                    "Order added: " + order
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }
    }

    // Chef takes an order
    public String takeOrder() {

        try {

            String order = orders.take();

            System.out.println(
                    "Chef received: " + order
            );

            return order;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            return null;

        }
    }
}

