import java.util.ArrayList;
import java.util.List;

public class MemoryLeakSimulator {

    // Static collection retains references permanently
    private static final List<Employee> employees =
            new ArrayList<>();

    public static void createObjects() {

        for (int i = 0; i < 1000; i++) {

            Employee employee = new Employee(i);

            // Objects remain referenced in the list
            employees.add(employee);
        }

        System.out.println(
                "Total objects retained: " + employees.size()
        );
    }

    public static void clearEmployees() {

        employees.clear();

        System.out.println(
                "Employee references cleared!"
        );
    }
}
