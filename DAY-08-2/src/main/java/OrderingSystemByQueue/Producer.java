package OrderingSystemByQueue;

public class Producer implements Runnable {

    private final OrderQueue orderQueue;

    public Producer(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {

        String[] orders = {
                "Order1",
                "Order2",
                "Order3",
                "Order4",
                "Order5"
        };

        for (String order : orders) {

            orderQueue.placeOrder(order);

        }
    }
}