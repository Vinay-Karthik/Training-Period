package com.example.employeesearch.dto;

import com.example.employeesearch.model.Department;

public record PayrollResult(
        String employeeId,
        String employeeName,
        Department department,
        double annualGrossSalary,
        double annualTax,
        double annualNetSalary,
        double monthlyGrossSalary,
        double monthlyTax,
        double monthlyNetSalary
) {
}
