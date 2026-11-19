package com.company.payroll.service;

import com.company.payroll.model.ContractEmployee;
import com.company.payroll.model.Employee;
import com.company.payroll.model.PermanentEmployee;

/**
 * SRP: Single Responsibility Principle.
 * Has only ONE job: compute statutory payroll deductions (PF, Health Insurance, etc.).
 */
public class DeductionService {

    public double calculateDeductions(Employee employee, double grossSalary) {
        // Contract employees do not have statutory PF or health insurance deductions
        if (employee instanceof ContractEmployee) {
            return 0.0;
        }

        double pf = 0.0;
        if (employee instanceof PermanentEmployee perm) {
            pf = perm.getBasicSalary() * 0.12; // 12% of basic
        } else {
            pf = grossSalary * 0.06; // standard deduction for other permanent/sales/exec
        }

        double healthInsurance = 1500.0;
        double professionalTax = (grossSalary > 15000) ? 200.0 : 0.0;

        return pf + healthInsurance + professionalTax;
    }
}
