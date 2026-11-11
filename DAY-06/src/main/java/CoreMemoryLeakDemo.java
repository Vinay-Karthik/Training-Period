import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// cmds to compile javac -d .\DAY-06\target\classes .\DAY-06\src\main\java\CoreMemoryLeakDemo.java
// cmds to run java -Xms32m -Xmx128m -cp .\DAY-06\target\classes CoreMemoryLeakDemo
public class CoreMemoryLeakDemo {

    // GC Root: Objects attached to this static collection cannot be garbage collected
    private static final List<LeakyObject> LEAK_CACHE = new ArrayList<>();

    // Simulates a heavy domain object retaining ~2MB of memory
    static class LeakyObject {
        private final String id;
        private final byte[] payload;

        public LeakyObject(int index) {
            this.id = "Payload-ID-" + index;
            this.payload = new byte[2 * 1024 * 1024]; // 2MB payload
        }
    }

    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("=================================================");
        System.out.println("  Core Java Memory Leak & Heap Analysis Demo    ");
        System.out.println("=================================================");
        System.out.println("Process PID : " + pid);
        System.out.println("Command to attach diagnostic tools:");
        System.out.println("  jmap -histo:live " + pid + " | head -n 15");
        System.out.println("=================================================\n");

        Scanner scanner = new Scanner(System.in);
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        while (true) {
            printMemoryStatus(memoryBean);
            System.out.println("\nOptions:");
            System.out.println("  1 -> Allocate 10MB (Simulate Leak)");
            System.out.println("  2 -> Run Explicit GC (System.gc())");
            System.out.println("  3 -> Clear Leaked Cache (Fix Leak)");
            System.out.println("  4 -> Exit");
            System.out.print("Select an option [1-4]: ");

            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\nAllocating 5 x 2MB objects into LEAK_CACHE...");
                    for (int i = 0; i < 5; i++) {
                        LEAK_CACHE.add(new LeakyObject(LEAK_CACHE.size() + 1));
                    }
                    break;

                case "2":
                    System.out.println("\nInvoking System.gc()...");
                    System.gc();
                    try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                    break;

                case "3":
                    System.out.println("\nClearing references from LEAK_CACHE...");
                    LEAK_CACHE.clear();
                    System.gc();
                    try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                    break;

                case "4":
                    System.out.println("Exiting application.");
                    return;

                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static void printMemoryStatus(MemoryMXBean memoryBean) {
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        long usedMB = heapUsage.getUsed() / (1024 * 1024);
        long maxMB = heapUsage.getMax() / (1024 * 1024);
        long committedMB = heapUsage.getCommitted() / (1024 * 1024);

        System.out.println("\n--- Heap Telemetry ---");
        System.out.println("Retained Objects : " + LEAK_CACHE.size());
        System.out.println("Used Heap        : " + usedMB + " MB");
        System.out.println("Committed Heap   : " + committedMB + " MB");
        System.out.println("Max Heap         : " + maxMB + " MB");
    }
}