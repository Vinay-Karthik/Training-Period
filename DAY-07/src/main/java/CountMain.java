import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CountMain {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> counter =
                new ConcurrentHashMap<>();

        CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.merge(1, 1, Integer::sum);
            }
            latch.countDown();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

//        t1.join();
//        t2.join();
//        t3.join();

        latch.await();

        System.out.println("\nConcurrent Counter: "
                + counter.get(1));
    }
}
