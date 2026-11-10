package com.company.hr.model;

/**
 * Record demonstrating Java 17-21 lightweight immutable record feature (Module 1).
 */
public record EmployeeSummary(
        String id,
        String name,
        Department department,
        Designation designation,
        double annualSalary
) {
    public static EmployeeSummary from(Employee employee) {
        return new EmployeeSummary(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.calculateAnnualGrossSalary()
        );
    }
}
