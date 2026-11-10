package com.company.hr.model;

public non-sealed class PermanentEmployee extends Employee {

    private double annualBonusPercentage;

    public PermanentEmployee(String id, String name, String email, Department department, Designation designation, double annualSalary) {
        super(id, name, email, department, designation, EmploymentType.PERMANENT, annualSalary);
        this.annualBonusPercentage = 0.10; // Default 10% annual bonus capability
    }

    public PermanentEmployee(String id, String name, String email, Department department, Designation designation, EmploymentType type, double annualSalary) {
        super(id, name, email, department, designation, type, annualSalary);
        this.annualBonusPercentage = 0.0;
    }

    @Override
    public double calculateAnnualGrossSalary() {
        return getBaseSalary() * (1 + annualBonusPercentage);
    }

    public double getAnnualBonusPercentage() {
        return annualBonusPercentage;
    }

    public void setAnnualBonusPercentage(double annualBonusPercentage) {
        this.annualBonusPercentage = annualBonusPercentage;
    }
}
