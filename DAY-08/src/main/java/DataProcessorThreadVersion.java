import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataProcessorThreadVersion {
    private static final int THREAD_COUNT = 20;

    public void process(List<Customer> customers) {
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        int chunkSize = customers.size() / THREAD_COUNT;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 1; i <= THREAD_COUNT; i++) {
            int fromIndex = i * chunkSize;

            // we use this approach because if the list contains some odd numbers , the last remaining numbers will be uplifted by last thread
            int toIndex = (i == THREAD_COUNT) ? customers.size() : (i + 1) * chunkSize;

            List<Customer> subList = customers.subList(fromIndex, toIndex);

            tasks.add(()->{
                for (Customer customer:subList){
                    transform(customer);
                }
                return null;
            });
        }

        try{
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            executor.shutdown();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time taken: "+ (end-start));

    }
    private void transform(Customer customer){
        String email= customer.getEmail();
        double bonus = customer.getSalary()*0.2;
        customer.setEmail(email.toUpperCase());
        customer.setSalary(bonus);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
