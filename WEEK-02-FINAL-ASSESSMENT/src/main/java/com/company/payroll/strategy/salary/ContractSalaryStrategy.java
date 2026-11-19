package com.company.payroll.strategy.salary;

import com.company.payroll.model.ContractEmployee;
import com.company.payroll.model.Employee;

/**
 * Contract Employee Compensation: Fixed contract amount.
 */
public class ContractSalaryStrategy implements SalaryStrategy {

    @Override
    public double calculateGross(Employee employee) {
        if (employee instanceof ContractEmployee cont) {
            return cont.getContractAmount();
        }
        return 0.0;
    }
}
