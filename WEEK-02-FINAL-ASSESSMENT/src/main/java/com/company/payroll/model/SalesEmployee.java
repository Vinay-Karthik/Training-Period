package com.company.payroll.model;

import com.company.payroll.model.interfaces.HasCommission;

/**
 * Sales Employee: Base Salary + Sales Commission.
 */
public class SalesEmployee extends Employee implements HasCommission {
    private final double baseSalary;
    private final double salesAmount;
    private final double commissionRate;

    public SalesEmployee(String id, String name, String department, String taxRegime, 
                         String email, double baseSalary, double salesAmount, double commissionRate) {
        super(id, name, EmployeeType.SALES, department, taxRegime, email);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double getBaseSalary() { return baseSalary; }

    @Override
    public double getSalesAmount() { return salesAmount; }

    @Override
    public double getCommissionRate() { return commissionRate; }
}
