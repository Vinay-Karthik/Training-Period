package OrderingSystem;

import OrderingSystem.Consumer;
import OrderingSystem.OrderQueue;
import OrderingSystem.Producer;

public class Main {

    public static void main(String[] args) {

        OrderQueue orderQueue = new OrderQueue();

        Thread chef = new Thread(
                new Consumer(orderQueue),
                "Chef"
        );

        Thread customer = new Thread(
                new Producer(orderQueue),
                "Customer"
        );

        chef.start();
        customer.start();
    }
}
