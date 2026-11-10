public class JVMInfo {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Java Version: " + Runtime.version());

        System.out.println("JVM Name: " + System.getProperty("java.vm.name"));

        System.out.println("JVM Vendor: " + System.getProperty("java.vm.vendor"));

        System.out.println("Processors: " + runtime.availableProcessors());

        double maxMemory = runtime.maxMemory() / 1e9;
        System.out.println("Max Memory: " + String.format("%.3f GB", maxMemory));

        double totalMemory = runtime.totalMemory() / 1e9;
        System.out.println("Total Memory: " + String.format("%.3f GB", totalMemory));

        double freeMemory = runtime.freeMemory() / 1e9;
        System.out.println("freeMemory : " + String.format("%.3f GB", freeMemory));
    }
}