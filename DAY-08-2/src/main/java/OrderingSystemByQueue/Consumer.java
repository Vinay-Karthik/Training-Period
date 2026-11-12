package OrderingSystemByQueue;

public class Consumer implements Runnable {

    private OrderQueue orderQueue;

    public Consumer(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String order = orderQueue.takeOrder();
            if (order == null) {
                return;
            }
            System.out.println(
                    "Preparing: " + order
            );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(
                    "Completed: " + order);
        }
    }
}
