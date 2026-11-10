import java.util.concurrent.*;

public class UsingFutureCount {

    public static void main(String[] args) throws Exception {

        ConcurrentHashMap<Integer, Integer> counter =
                new ConcurrentHashMap<>();

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        Callable<Void> task = () -> {

            for (int i = 0; i < 1000; i++) {
                counter.merge(1, 1, Integer::sum);
            }

            return null;
        };

        // Submit tasks
        Future<Void> f1 = executor.submit(task);
        Future<Void> f2 = executor.submit(task);
        Future<Void> f3 = executor.submit(task);

        // Wait for completion
        f1.get();
        f2.get();
        f3.get();

        System.out.println("Counter: " + counter.get(1));

        executor.shutdown();
    }
}
