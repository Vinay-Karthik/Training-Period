package com.company.employee.service;

import com.company.employee.model.Employee;
import com.company.employee.model.EmploymentType;
import com.company.employee.service.impl.ContractSalaryCalculator;
import com.company.employee.service.impl.ManagerSalaryCalculator;
import com.company.employee.service.impl.PermanentSalaryCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EmployeeService {

    private static final List<Employee> employees = new ArrayList<>();
    private static final SalaryCalculator permanentCalculator = new PermanentSalaryCalculator();
    private static final SalaryCalculator contractCalculator = new ContractSalaryCalculator();
    private static final SalaryCalculator managerCalculator = new ManagerSalaryCalculator();
    private static int totalEmployees = 0;
    private static long idSequence = 1;

    public static long nextEmployeeId() {
        return idSequence++;
    }

    public static void addEmployee(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (employees.contains(employee)) {
            throw new IllegalArgumentException("Employee with ID " + employee.getEmployeeId() + " already exists");
        }
        employees.add(employee);
        totalEmployees++;
    }

    public static int getEmployeeCount() {
        return totalEmployees;
    }

    public static List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }

    // Overload 1: search by employee ID
    public static Employee searchEmployee(long employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    // Overload 2: search by name (returns all matches)
    public static List<Employee> searchEmployee(String name) {
        List<Employee> matches = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name.trim())) {
                matches.add(employee);
            }
        }
        return matches;
    }

    // Overload 3: search by name and department
    public static List<Employee> searchEmployee(String name, String departmentName) {
        List<Employee> matches = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name.trim())
                    && employee.getDepartment().getName().equalsIgnoreCase(departmentName.trim())) {
                matches.add(employee);
            }
        }
        return matches;
    }

    public static void sortById() {
        employees.sort(null); // natural ordering via Comparable
    }

    public static void sortBySalary() {
        employees.sort(EmployeeComparators.BY_SALARY);
    }

    public static void sortByName() {
        employees.sort(EmployeeComparators.BY_NAME);
    }

    public static double calculateMonthlySalary(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        return switch (employee.getEmploymentType()) {
            case PERMANENT -> permanentCalculator.calculateMonthlySalary(employee);
            case CONTRACT -> contractCalculator.calculateMonthlySalary(employee);
            case MANAGER -> managerCalculator.calculateMonthlySalary(employee);
        };
    }

    public static void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees registered yet.");
            return;
        }
        System.out.println("========== EMPLOYEE DETAILS ==========");
        System.out.println();
        for (Employee employee : employees) {
            employee.displayDetails();
            System.out.println();
        }
        System.out.println("Total Employees: " + totalEmployees);
    }
}
