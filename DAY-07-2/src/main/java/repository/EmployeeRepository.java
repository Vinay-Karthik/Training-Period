package repository;

import model.Employee;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeRepository {
    private final Map<String, Employee> employees = new ConcurrentHashMap<>();

    public void addEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");
        if (employees.putIfAbsent(employee.id(), employee) != null) {
            throw new IllegalArgumentException("Duplicate Employee ID detected: " + employee.id());
        }
    }

    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employees.get(id));
    }

    public List<Employee> findAll() {
        return List.copyOf(employees.values());
    }

    public int size() {
        return employees.size();
    }
}
