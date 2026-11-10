package com.example.employeesearch.repository;

import com.example.employeesearch.model.Employee;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentEmployeeRepository {
    private final ConcurrentHashMap<String, Employee> employeeMap = new ConcurrentHashMap<>();

    public boolean addEmployee(Employee employee) {
        return employeeMap.putIfAbsent(employee.employeeId(), employee) == null;
    }

    public Optional<Employee> findById(String employeeId) {
        return Optional.ofNullable(employeeMap.get(employeeId));
    }

    public boolean updateEmployee(Employee employee) {
        return employeeMap.replace(employee.employeeId(), employee) != null;
    }

    public boolean deleteEmployee(String employeeId) {
        return employeeMap.remove(employeeId) != null;
    }

    public Employee findOrCreateEmployee(String employeeId, java.util.function.Function<String, Employee> mappingFunction) {
        return employeeMap.computeIfAbsent(employeeId, mappingFunction);
    }

    public Employee updateSalary(String employeeId, double newSalary) {
        return employeeMap.computeIfPresent(employeeId, (id, emp) ->
                new Employee(
                        emp.employeeId(),
                        emp.name(),
                        emp.email(),
                        newSalary,
                        emp.department(),
                        emp.designation(),
                        emp.employmentType()
                )
        );
    }

    public int count() {
        return employeeMap.size();
    }
}
