package com.company.payroll.model;

import com.company.payroll.model.interfaces.HasExecutiveBenefits;

/**
 * Executive Employee: Fixed Executive Salary + Bonus + Executive Benefits.
 */
public class ExecutiveEmployee extends Employee implements HasExecutiveBenefits {
    private final double fixedSalary;
    private final double bonus;
    private final double benefits;

    public ExecutiveEmployee(String id, String name, String department, String taxRegime, 
                             String email, double fixedSalary, double bonus, double benefits) {
        super(id, name, EmployeeType.EXECUTIVE, department, taxRegime, email);
        this.fixedSalary = fixedSalary;
        this.bonus = bonus;
        this.benefits = benefits;
    }

    @Override
    public double getFixedSalary() { return fixedSalary; }

    @Override
    public double getBonus() { return bonus; }

    @Override
    public double getBenefits() { return benefits; }
}
