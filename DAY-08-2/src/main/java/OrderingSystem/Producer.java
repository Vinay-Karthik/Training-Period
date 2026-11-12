package OrderingSystem;

public class Producer implements Runnable {

    private final OrderQueue orderQueue;

    public Producer(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {

        String[] orders = {
                "Order1","Order2", "Order3"
        };
        for (String order : orders) {
            orderQueue.placeOrder(order);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
