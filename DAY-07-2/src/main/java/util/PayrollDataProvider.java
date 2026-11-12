package util;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class PayrollDataProvider {

    public static List<Employee> generateEmployees(int count) {
        List<Employee> list = new ArrayList<>();
        String[] departments = {"Engineering", "Finance", "HR", "Sales", "Marketing"};
        String[] designations = {"Junior", "Mid-Level", "Senior", "Lead", "Manager"};

        for (int i = 1; i <= count; i++) {
            String id = String.format("EMP-%04d", i);
            String name = "Employee " + i;
            String dept = departments[i % departments.length];
            String desig = designations[i % designations.length];
            double salary = 50000 + (i * 500);

            list.add(new Employee(id, name, dept, desig, salary));
        }
        return list;
    }
}
