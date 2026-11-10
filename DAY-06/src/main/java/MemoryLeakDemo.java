import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

    private static final List<byte[]> CACHE =
            new ArrayList<>();

    public static void main(String[] args)
            throws InterruptedException {

        while (true) {

            CACHE.add(new byte[1024 * 1024]);

            System.out.println(
                    "Retained objects: " + CACHE.size()
            );

            Thread.sleep(500);
        }
    }
}