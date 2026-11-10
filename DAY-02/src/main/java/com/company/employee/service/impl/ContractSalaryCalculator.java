package com.company.employee.service.impl;

import com.company.employee.model.Employee;
import com.company.employee.service.SalaryCalculator;

public class ContractSalaryCalculator implements SalaryCalculator {
    @Override
    public double calculateMonthlySalary(Employee employee) {
        return employee.getAnnualSalary() / 12.0;
    }
}
