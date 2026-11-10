package com.example.employeesearch.util;

import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Designation;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.model.EmploymentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PayrollDataProvider {

    public static List<Employee> getSampleEmployees() {
        return List.of(
                new Employee("EMP001", "Rahul", Department.ENGINEERING, Designation.SENIOR_ENGINEER, EmploymentType.FULL_TIME, 750000.0),
                new Employee("EMP002", "Priya", Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.FULL_TIME, 950000.0),
                new Employee("EMP003", "Arjun", Department.ENGINEERING, Designation.LEAD_ENGINEER, EmploymentType.FULL_TIME, 1200000.0),
                new Employee("EMP004", "Sneha", Department.FINANCE, Designation.FINANCIAL_ANALYST, EmploymentType.FULL_TIME, 550000.0),
                new Employee("EMP005", "Kiran", Department.SALES, Designation.SALES_EXECUTIVE, EmploymentType.FULL_TIME, 650000.0),
                new Employee("EMP006", "Ananya", Department.ENGINEERING, Designation.SENIOR_ENGINEER, EmploymentType.FULL_TIME, 1050000.0),
                new Employee("EMP007", "Vikram", Department.HUMAN_RESOURCES, Designation.HR_ASSOCIATE, EmploymentType.FULL_TIME, 700000.0),
                new Employee("EMP008", "Meera", Department.FINANCE, Designation.FINANCE_MANAGER, EmploymentType.FULL_TIME, 850000.0),
                new Employee("EMP009", "Rohit", Department.ENGINEERING, Designation.SOFTWARE_ENGINEER, EmploymentType.FULL_TIME, 250000.0),
                new Employee("EMP010", "Divya", Department.SALES, Designation.SALES_MANAGER, EmploymentType.FULL_TIME, 800000.0)
        );
    }

    public static List<List<Employee>> getDepartmentEmployeeLists() {
        List<Employee> all = getSampleEmployees();
        return List.of(
                all.stream().filter(e -> e.department() == Department.ENGINEERING).toList(),
                all.stream().filter(e -> e.department() == Department.HUMAN_RESOURCES).toList(),
                all.stream().filter(e -> e.department() == Department.FINANCE).toList(),
                all.stream().filter(e -> e.department() == Department.SALES).toList()
        );
    }

    public static List<Employee> generateLargeDataset(int count) {
        List<Employee> list = new ArrayList<>(count);
        Department[] depts = Department.values();
        Designation[] desgs = Designation.values();
        EmploymentType[] types = EmploymentType.values();
        Random random = new Random(42);

        for (int i = 1; i <= count; i++) {
            String id = String.format("EMP%06d", i);
            String name = "Emp_" + i;
            Department dept = depts[random.nextInt(depts.length)];
            Designation desg = desgs[random.nextInt(desgs.length)];
            EmploymentType type = types[random.nextInt(types.length)];
            double salary = 200000 + (1500000 - 200000) * random.nextDouble();
            list.add(new Employee(id, name, dept, desg, type, Math.round(salary * 100.0) / 100.0));
        }
        return list;
    }
}
