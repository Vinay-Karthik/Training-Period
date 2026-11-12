import java.util.HashMap;
import java.util.Map;

public class MapIteration {
    public void calMapIteration(){
        Map<Integer, String> students = new HashMap<>();

        students.put(101, "Rahul");
        students.put(102, "Priya");
        students.put(103, "Amit");

        for (Map.Entry<Integer, String> entry : students.entrySet()) {

            System.out.println(
                    entry.getKey() + " : " + entry.getValue()
            );
        }
    }
}
