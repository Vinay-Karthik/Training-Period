import java.util.concurrent.*;

public class FutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> call1 = () -> {
            try {
                Thread.sleep(2000);
                return "Vinay";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Callable<String> call2 = () -> {
            try {
                Thread.sleep(2000);
                return "Karthik";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Future<String> future1 = executor.submit(call1);
        Future<String> future2 = executor.submit(call2);

        String firstName = future1.get();
        String lastname = future2.get();
        System.out.println(firstName + " " + lastname);

        System.out.println(System.currentTimeMillis() - start);

//        long start = System.currentTimeMillis();
//
//        FutureDemo3Service futureDemo3Service = new FutureDemo3Service();
//
//        String firstName = futureDemo3Service.firstName();
//        String lastName = futureDemo3Service.lastName();
//        String concatinated = firstName+" "+ lastName;
//        System.out.println(concatinated);
//
//        System.out.println(System.currentTimeMillis() - start);


    }
}
