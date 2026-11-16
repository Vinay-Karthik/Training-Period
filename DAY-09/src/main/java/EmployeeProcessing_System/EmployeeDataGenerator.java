package EmployeeProcessing_System;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataGenerator {

    public static List<Employee> generate(int count) {
        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            employees.add(new Employee(
                    i,
                    "Employee-" + i,
                    i % 2 == 0 ? "Engineering" : "Finance",
                    "employee" + i + "@company.com"
            ));
        }

        return employees;
    }
}
