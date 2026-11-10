package com.example.employeesearch.service;

import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalyticsService {

    public double calculateTotalSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .sum();
    }

    public double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);
    }

    public Optional<Employee> findHighestPaidEmployee(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));
    }

    public Optional<Employee> findLowestPaidEmployee(List<Employee> employees) {
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::salary));
    }

    public Map<Department, Double> calculateDepartmentSalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)
                ));
    }

    public Map<Department, Double> calculateDepartmentAverageSalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));
    }

    public Map<Department, Long> countEmployeesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()
                ));
    }

    public Map<Department, Double> calculateSalaryPercentage(List<Employee> employees) {
        double total = calculateTotalSalary(employees);
        if (total == 0.0) return Map.of();

        Map<Department, Double> deptTotals = calculateDepartmentSalary(employees);
        Map<Department, Double> percentages = new EnumMap<>(Department.class);

        deptTotals.forEach((dept, salary) ->
                percentages.put(dept, (salary / total) * 100.0)
        );
        return percentages;
    }

    public List<Employee> findAboveDepartmentAverage(List<Employee> employees) {
        Map<Department, Double> deptAverages = calculateDepartmentAverageSalary(employees);
        return employees.stream()
                .filter(emp -> emp.salary() > deptAverages.getOrDefault(emp.department(), 0.0))
                .collect(Collectors.toList());
    }

    public DoubleSummaryStatistics getSalaryStatistics(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .summaryStatistics();
    }

    public void printSalaryReport(List<Employee> employees) {
        double totalSalary = calculateTotalSalary(employees);
        double avgSalary = calculateAverageSalary(employees);
        Optional<Employee> highest = findHighestPaidEmployee(employees);
        Optional<Employee> lowest = findLowestPaidEmployee(employees);
        Map<Department, Double> deptSalaries = calculateDepartmentSalary(employees);

        System.out.println("========================================");
        System.out.println("        EMPLOYEE SALARY REPORT          ");
        System.out.println("========================================");
        System.out.println("Total Employees: " + employees.size());
        System.out.printf("Total Monthly Payroll: %.2f%n", totalSalary);
        System.out.printf("Average Salary: %.2f%n", avgSalary);
        System.out.println();
        highest.ifPresent(e -> System.out.printf("Highest Paid Employee: %s%nHighest Salary: %.2f%n%n", e.name(), e.salary()));
        lowest.ifPresent(e -> System.out.printf("Lowest Paid Employee: %s%nLowest Salary: %.2f%n%n", e.name(), e.salary()));
        System.out.println("Department-wise Salary:");
        deptSalaries.forEach((dept, sal) -> System.out.printf("%-18s %.2f%n", dept, sal));
        System.out.println("========================================");
    }
}
