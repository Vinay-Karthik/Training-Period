
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentHashMapExample {

    public static void main(String[] args) throws InterruptedException {

        // 1. Creating ConcurrentHashMap
        ConcurrentHashMap<Integer, String> map =
                new ConcurrentHashMap<>();

        // 2. put() - Adding elements
        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "Spring");

        System.out.println("Initial Map: " + map);

        // 3. get() - Retrieve value
        System.out.println("Value: " + map.get(1));

        // 4. putIfAbsent() - Insert only if key is absent
        map.putIfAbsent(1, "C++");
        map.putIfAbsent(4, "SQL");

        System.out.println("After putIfAbsent: " + map);

        // 5. computeIfAbsent()
        map.computeIfAbsent(5, key -> "React");

        // 6. computeIfPresent()
        map.computeIfPresent(2,
                (key, value) -> value + " Programming");

        System.out.println("After compute methods: " + map);

        // 7. merge() - Combine existing value
        map.merge(1, " Advanced",
                (oldValue, newValue) -> oldValue + newValue);

        System.out.println("After merge: " + map);

        // 8. replace() - Update existing value
        map.replace(3, "Hibernate");

        // 9. remove() - Delete entry
        map.remove(4);

        System.out.println("After replace and remove: " + map);

        // 10. forEach() - Iterate elements
        System.out.println("\nIterating Map:");

        map.forEach((key, value) ->
                System.out.println(key + " = " + value));


        Iterator<Map.Entry<Integer, String>> i = map.entrySet().iterator();

        while (i.hasNext()) {
            ConcurrentHashMap.Entry<Integer, String> entry
                    = i.next();
            System.out.println("Key = " + entry.getKey()
                    + ", Value = "
                    + entry.getValue());
        }
    }
}
