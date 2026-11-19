package com.company.payroll.model;

import com.company.payroll.model.interfaces.HasHra;

/**
 * Permanent Employee: Basic + HRA + Allowances.
 */
public class PermanentEmployee extends Employee implements HasHra {
    private final double basicSalary;
    private final double hra;
    private final double allowances;

    public PermanentEmployee(String id, String name, String department, String taxRegime, 
                             String email, double basicSalary, double hra, double allowances) {
        super(id, name, EmployeeType.PERMANENT, department, taxRegime, email);
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.allowances = allowances;
    }

    @Override
    public double getBasicSalary() { return basicSalary; }

    @Override
    public double getHra() { return hra; }

    @Override
    public double getAllowances() { return allowances; }
}
