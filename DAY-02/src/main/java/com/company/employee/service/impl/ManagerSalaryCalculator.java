package com.company.employee.service.impl;

import com.company.employee.model.Employee;
import com.company.employee.model.Manager;
import com.company.employee.service.SalaryCalculator;

public class ManagerSalaryCalculator implements SalaryCalculator {
    private static final double MONTHLY_TEAM_ALLOWANCE = 500.0;

    @Override
    public double calculateMonthlySalary(Employee employee) {
        double monthly = employee.getAnnualSalary() / 12.0;
        if (employee instanceof Manager manager) {
            monthly += manager.getTeamSize() * MONTHLY_TEAM_ALLOWANCE;
        }
        return monthly;
    }
}
