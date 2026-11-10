package com.example.employeesearch.repository;

import com.example.employeesearch.model.Employee;

import java.util.*;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private final Set<String> employeeIds = new HashSet<>();
    private final Map<String, Employee> employeeMap = new HashMap<>();
    private final LinkedHashMap<String, Employee> orderedEmployees = new LinkedHashMap<>();

    public boolean addEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");
        if (employeeIds.contains(employee.employeeId())) {
            return false;
        }
        employees.add(employee);
        employeeIds.add(employee.employeeId());
        employeeMap.put(employee.employeeId(), employee);
        orderedEmployees.put(employee.employeeId(), employee);
        return true;
    }

    public Optional<Employee> findById(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(employeeMap.get(employeeId));
    }

    public List<Employee> findAll() {
        return List.copyOf(employees);
    }

    public boolean deleteEmployee(String employeeId) {
        if (employeeId == null || !employeeIds.contains(employeeId)) {
            return false;
        }
        Employee employee = employeeMap.remove(employeeId);
        employees.remove(employee);
        employeeIds.remove(employeeId);
        orderedEmployees.remove(employeeId);
        return true;
    }

    public boolean existsById(String employeeId) {
        return employeeId != null && employeeIds.contains(employeeId);
    }

    public int count() {
        return employees.size();
    }

    public List<Employee> getOrderedEmployees() {
        return List.copyOf(orderedEmployees.values());
    }
}
