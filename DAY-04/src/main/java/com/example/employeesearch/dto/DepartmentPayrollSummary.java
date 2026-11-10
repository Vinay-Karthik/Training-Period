package com.example.employeesearch.dto;

import com.example.employeesearch.model.Department;

public record DepartmentPayrollSummary(
        Department department,
        long employeeCount,
        double totalAnnualSalary,
        double averageAnnualSalary,
        double highestSalary,
        double lowestSalary,
        double totalAnnualTax
) {
}
