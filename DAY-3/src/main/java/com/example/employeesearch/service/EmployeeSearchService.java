package com.example.employeesearch.service;

import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.model.EmploymentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class EmployeeSearchService {

    public <T> List<T> filter(List<T> items, Predicate<T> condition) {
        Objects.requireNonNull(items, "Items list cannot be null");
        Objects.requireNonNull(condition, "Condition cannot be null");

        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }
        return List.copyOf(result);
    }

    public List<Employee> searchById(List<Employee> employees, String employeeId) {
        return filter(employees, emp -> emp.employeeId().equalsIgnoreCase(employeeId));
    }

    public List<Employee> searchByName(List<Employee> employees, String name) {
        if (name == null || name.isBlank()) return List.of();
        return filter(employees, emp -> emp.name().toLowerCase().contains(name.toLowerCase()));
    }

    public List<Employee> searchByDepartment(List<Employee> employees, Department department) {
        return filter(employees, emp -> emp.department() == department);
    }

    public List<Employee> searchByEmploymentType(List<Employee> employees, EmploymentType type) {
        return filter(employees, emp -> emp.employmentType() == type);
    }

    public List<Employee> searchByMinimumSalary(List<Employee> employees, double salary) {
        return filter(employees, emp -> emp.salary() >= salary);
    }

    public List<Employee> searchByCustomCondition(List<Employee> employees, Predicate<Employee> condition) {
        return filter(employees, condition);
    }

    public <T extends Employee> double calculateTotalSalary(List<T> employees) {
        double total = 0.0;
        for (T emp : employees) {
            total += emp.salary();
        }
        return total;
    }

    public void processEmployeesPECS(List<? extends Employee> producerList, List<? super Employee> consumerList) {
        for (Employee emp : producerList) {
            consumerList.add(emp);
        }
    }
}
