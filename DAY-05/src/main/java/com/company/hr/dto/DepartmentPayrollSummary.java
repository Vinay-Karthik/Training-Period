package com.company.hr.dto;

import com.company.hr.model.Department;

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
