package com.company.hr.service;

import com.company.hr.model.Department;
import com.company.hr.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeSearchService {

    public List<Employee> searchByName(List<Employee> employees, String query) {
        if (query == null || query.isBlank()) return List.of();
        String lowerQuery = query.toLowerCase();
        return employees.stream()
                .filter(e -> e.getName().toLowerCase().contains(lowerQuery))
                .toList();
    }

    public List<Employee> filterByDepartment(List<Employee> employees, Department department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .toList();
    }

    public List<Employee> filterBySalaryRange(List<Employee> employees, double minSalary, double maxSalary) {
        return employees.stream()
                .filter(e -> {
                    double sal = e.calculateAnnualGrossSalary();
                    return sal >= minSalary && sal <= maxSalary;
                })
                .toList();
    }

    public List<Employee> sortByName(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .toList();
    }

    public List<Employee> sortBySalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::calculateAnnualGrossSalary).reversed())
                .toList();
    }

    public Map<Department, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
