package com.company.payroll.strategy.salary;

import com.company.payroll.model.Employee;
import com.company.payroll.model.PermanentEmployee;

/**
 * Permanent Employee Salary: Basic + HRA + Allowances.
 */
public class PermanentSalaryStrategy implements SalaryStrategy {

    @Override
    public double calculateGross(Employee employee) {
        if (employee instanceof PermanentEmployee perm) {
            return perm.getBasicSalary() + perm.getHra() + perm.getAllowances();
        }
        return 0.0;
    }
}
