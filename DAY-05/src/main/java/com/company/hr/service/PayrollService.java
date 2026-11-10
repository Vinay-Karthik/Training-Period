package com.company.hr.service;

import com.company.hr.dto.PayrollResult;
import com.company.hr.exception.PayrollProcessingException;
import com.company.hr.model.Employee;

import java.util.List;

public class PayrollService {

    private final TaxService taxService;

    public PayrollService(TaxService taxService) {
        this.taxService = taxService;
    }

    public PayrollResult processEmployeePayroll(Employee employee) {
        if (employee == null) {
            throw new PayrollProcessingException("Employee record cannot be null for payroll calculation.");
        }
        try {
            double annualGross = employee.calculateAnnualGrossSalary();
            double annualTax = taxService.calculateAnnualTax(annualGross);
            double annualNet = taxService.calculateAnnualNetSalary(annualGross);

            double monthlyGross = annualGross / 12.0;
            double monthlyTax = annualTax / 12.0;
            double monthlyNet = annualNet / 12.0;

            return new PayrollResult(
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    annualGross,
                    annualTax,
                    annualNet,
                    monthlyGross,
                    monthlyTax,
                    monthlyNet
            );
        } catch (Exception e) {
            throw new PayrollProcessingException("Error processing payroll for employee ID: " + employee.getId(), e);
        }
    }

    public List<PayrollResult> processPayrollForAll(List<Employee> employees) {
        return employees.stream()
                .map(this::processEmployeePayroll)
                .toList();
    }

    public double calculateTotalPayrollExpenditure(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::calculateAnnualGrossSalary)
                .sum();
    }

    public double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::calculateAnnualGrossSalary)
                .average()
                .orElse(0.0);
    }
}
