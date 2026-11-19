package com.company.payroll.strategy.salary;

import com.company.payroll.model.Employee;
import com.company.payroll.model.ExecutiveEmployee;

/**
 * Executive Employee Compensation: Fixed Salary + Bonus + Benefits.
 */
public class ExecutiveSalaryStrategy implements SalaryStrategy {

    @Override
    public double calculateGross(Employee employee) {
        if (employee instanceof ExecutiveEmployee exec) {
            return exec.getFixedSalary() + exec.getBonus() + exec.getBenefits();
        }
        return 0.0;
    }
}
