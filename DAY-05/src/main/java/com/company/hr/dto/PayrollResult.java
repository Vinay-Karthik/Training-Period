package com.company.hr.dto;

import com.company.hr.model.Department;

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
