import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTravesing {
    public static void concurrentTravesing() {
        ConcurrentHashMap<Integer, String> map =
                new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "SQL");

        for (Integer key : map.keySet()) {

            System.out.println(key);

            map.put(4, "Spring Boot");
        }
    }
}
