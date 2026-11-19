package com.company.payroll.repository;

import com.company.payroll.model.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete in-memory implementation of EmployeeRepository.
 */
public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final Map<String, Employee> database = new HashMap<>();

    @Override
    public void save(Employee employee) {
        database.put(employee.getId(), employee);
    }

    @Override
    public Employee findById(String id) {
        return database.get(id);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(database.values());
    }
}
