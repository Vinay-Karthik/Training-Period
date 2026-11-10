
import java.util.ArrayList;
import java.util.List;

public class GarbageCollectionDemo {

    public static void main(String[] args) {

        List<byte[]> objects = new ArrayList<>();

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Before: "
                + runtime.freeMemory());

        for (int i = 0; i < 100_000; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            objects.add(new byte[1024]);
        }

        System.out.println("After allocation: "
                + runtime.freeMemory());

        objects.clear();

        System.gc();

        System.out.println("After GC request: "
                + runtime.freeMemory());
    }
}
