package Demos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureClass {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> future1 =
//                CompletableFuture.supplyAsync(() -> "Hello");
//
//        CompletableFuture<Void> future2 =
//                CompletableFuture.runAsync(() -> {
//                    System.out.println("Running");
//                });
//
//
//        CompletableFuture<Integer> future3 =
//                CompletableFuture.supplyAsync(() -> 10)
//                        .thenApply(number -> number * 2);

//        CompletableFuture.supplyAsync(() -> "Java")
//                .thenAccept(System.out::println);

//        System.out.println(future4.get());

//        CompletableFuture.supplyAsync(() -> "Java")
//                .thenRun(() ->
//                        System.out.println("Completed")
//                );

//        CompletableFuture<Integer> f1 =
//                CompletableFuture.supplyAsync(() -> 10);
//
//        CompletableFuture<Integer> f2 =
//                CompletableFuture.supplyAsync(() -> 20);
//
//        CompletableFuture<Integer> result =
//                f1.thenCombine(f2, Integer::sum);
//        System.out.println(result.get());


        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> {

                    if (true) {
                        throw new RuntimeException("Error");
                    }

                    return 100;

                }).exceptionally(ex -> 0);

        System.out.println(future.get());
    }
}
