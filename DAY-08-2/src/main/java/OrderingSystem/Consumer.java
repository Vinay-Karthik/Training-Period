package OrderingSystem;

public class Consumer implements Runnable {

    private final OrderQueue orderQueue;

    public Consumer(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            String order = orderQueue.takeOrder();
            if (order == null) {
                return;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(
                    "Chef completed: " + order
            );
        }
    }
}
