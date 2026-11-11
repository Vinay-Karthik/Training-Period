import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            int employeeId = i;
            executor.submit(() -> {
                System.out.println(employeeId + " "+ Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}