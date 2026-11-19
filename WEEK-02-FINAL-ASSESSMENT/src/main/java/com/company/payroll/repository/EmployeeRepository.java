package com.company.payroll.repository;

import com.company.payroll.model.Employee;
import java.util.List;

/**
 * DIP: Dependency Inversion Principle.
 * High-level payroll service depends on this abstraction, not a concrete database.
 */
public interface EmployeeRepository {
    void save(Employee employee);
    Employee findById(String id);
    List<Employee> findAll();
}
