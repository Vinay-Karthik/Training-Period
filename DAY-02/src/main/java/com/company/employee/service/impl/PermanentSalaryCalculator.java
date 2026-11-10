package com.company.employee.service.impl;

import com.company.employee.model.Employee;
import com.company.employee.model.PermanentEmployee;
import com.company.employee.service.SalaryCalculator;

public class PermanentSalaryCalculator implements SalaryCalculator {
    @Override
    public double calculateMonthlySalary(Employee employee) {
        double annualPay = employee.getAnnualSalary();
        if (employee instanceof PermanentEmployee permanent) {
            annualPay += permanent.getAnnualBonus();
        }
        return annualPay / 12.0;
    }
}
