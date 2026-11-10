//public class Main {
//
//    public static void main(String[] args)
//            throws InterruptedException {
//
//        Runtime runtime = Runtime.getRuntime();
//
//        while (true) {
//
//            MemoryLeakSimulator.createObjects();
//
//            long totalMemory =
//                    runtime.totalMemory();
//
//            long freeMemory =
//                    runtime.freeMemory();
//
//            long usedMemory =
//                    totalMemory - freeMemory;
//
//            System.out.println(
//                    "Used Heap Memory: "
//                            + usedMemory / (1024 * 1024)
//                            + " MB"
//            );
//
//            Thread.sleep(2000);
//        }
//
//    }
//}

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Runtime runtime = Runtime.getRuntime();

        for (int i = 1; i <= 10; i++) {

            MemoryLeakSimulator.createObjects();

            long used = runtime.totalMemory()
                    - runtime.freeMemory();

            System.out.println(
                    "Used Heap: "
                            + used / (1024 * 1024)
                            + " MB"
            );

            Thread.sleep(2000);
        }

        // Fix the simulated leak
        MemoryLeakSimulator.clearEmployees();

        // Request garbage collection
        System.gc();

        Thread.sleep(5000);

        long used = runtime.totalMemory()
                - runtime.freeMemory();

        System.out.println(
                "Heap after cleanup: "
                        + used / (1024 * 1024)
                        + " MB"
        );
    }
}