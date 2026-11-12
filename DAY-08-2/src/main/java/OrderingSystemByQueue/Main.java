package OrderingSystemByQueue;

public class Main {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();
        Thread chef = new Thread(
                new Consumer(orderQueue)
        );
        Thread customer = new Thread(
                new Producer(orderQueue)
        );
        chef.start();
        customer.start();
        try {
            customer.join();
            chef.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Restaurant closed!");
    }
}
