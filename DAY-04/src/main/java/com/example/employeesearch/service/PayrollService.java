package com.example.employeesearch.service;

import com.example.employeesearch.dto.PayrollResult;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.util.PayrollDataProvider;

import java.util.List;
import java.util.Optional;

public class PayrollService {

    private final TaxService taxService;

    public PayrollService(TaxService taxService) {
        this.taxService = taxService;
    }

    public PayrollResult processEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        double annualGross = employee.salary();
        double annualTax = taxService.calculateAnnualTax(annualGross);
        double annualNet = taxService.calculateAnnualNetSalary(annualGross);

        double monthlyGross = annualGross / 12.0;
        double monthlyTax = annualTax / 12.0;
        double monthlyNet = annualNet / 12.0;

        return new PayrollResult(
                employee.id(),
                employee.name(),
                employee.department(),
                annualGross,
                annualTax,
                annualNet,
                monthlyGross,
                monthlyTax,
                monthlyNet
        );
    }

    public List<PayrollResult> processPayroll(List<Employee> employees) {
        return employees.stream()
                .map(this::processEmployee)
                .toList();
    }

    // Module 8: Optional implementations
    public Optional<Employee> findEmployeeById(List<Employee> employees, String id) {
        return employees.stream()
                .filter(e -> e.id().equalsIgnoreCase(id))
                .findFirst();
    }

    public Employee getEmployeeOrDefault(List<Employee> employees, String id, Employee fallback) {
        return findEmployeeById(employees, id).orElse(fallback);
    }

    public Employee getEmployeeOrSupplier(List<Employee> employees, String id) {
        return findEmployeeById(employees, id).orElseGet(Employee::createDefault);
    }

    public void printEmployeeIfPresent(List<Employee> employees, String id) {
        findEmployeeById(employees, id).ifPresent(e ->
                System.out.println("Found Employee: " + e.name() + " (" + e.id() + ")")
        );
    }

    public Employee getRequiredEmployee(List<Employee> employees, String id) {
        return findEmployeeById(employees, id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + id));
    }
}
