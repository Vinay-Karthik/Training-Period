package com.company.payroll.strategy.salary;

import com.company.payroll.model.Employee;
import com.company.payroll.model.SalesEmployee;

/**
 * Sales Employee Compensation: Base Salary + (Sales * Commission Rate).
 */
public class SalesSalaryStrategy implements SalaryStrategy {

    @Override
    public double calculateGross(Employee employee) {
        if (employee instanceof SalesEmployee sales) {
            return sales.getBaseSalary() + (sales.getSalesAmount() * sales.getCommissionRate());
        }
        return 0.0;
    }
}
