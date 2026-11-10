package com.company.hr.service;

import com.company.hr.exception.EmployeeNotFoundException;
import com.company.hr.model.*;
import com.company.hr.repository.EmployeeRepository;
import com.company.hr.util.InputValidator;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void registerEmployee(String id, String name, String email, Department dept, Designation desg, EmploymentType type, double salary) {
        InputValidator.validateEmployeeInput(id, name, email, salary);

        // Pattern matching & switch expression usage (Module 1)
        Employee employee = switch (type) {
            case PERMANENT -> new PermanentEmployee(id, name, email, dept, desg, salary);
            case CONTRACT -> new ContractEmployee(id, name, email, dept, desg, salary, 12);
            case INTERN -> new PermanentEmployee(id, name, email, dept, desg, type, salary);
        };

        repository.save(employee);
    }

    public Employee getEmployeeById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID '" + id + "' not found."));
    }

    public Optional<Employee> findEmployeeById(String id) {
        return repository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public boolean updateEmployee(String id, String newName, String newEmail, Department newDept, Designation newDesg, double newSalary) {
        Employee existing = getEmployeeById(id);
        InputValidator.validateEmployeeInput(id, newName, newEmail, newSalary);

        existing.setName(newName);
        existing.setEmail(newEmail);
        existing.setDepartment(newDept);
        existing.setDesignation(newDesg);
        existing.setBaseSalary(newSalary);
        return true;
    }

    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }

    public String handleEmploymentTypePolicy(Employee employee) {
        // Module 1 Switch expression with pattern matching
        return switch (employee) {
            case Manager m -> "Executive Policy: Management allowance ₹" + m.getManagementAllowance();
            case PermanentEmployee p -> "Standard Corporate Policy: Full benefits & annual bonus option.";
            case ContractEmployee c -> "Contractor Policy: Duration " + c.getContractDurationMonths() + " months.";
        };
    }
}
