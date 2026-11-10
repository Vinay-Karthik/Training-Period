package com.company.hr.model;

public non-sealed class Manager extends Employee {

    private double managementAllowance;

    public Manager(String id, String name, String email, Department department, Designation designation, double annualSalary, double managementAllowance) {
        super(id, name, email, department, designation, EmploymentType.PERMANENT, annualSalary);
        this.managementAllowance = managementAllowance;
    }

    @Override
    public double calculateAnnualGrossSalary() {
        return getBaseSalary() + managementAllowance;
    }

    public double getManagementAllowance() {
        return managementAllowance;
    }

    public void setManagementAllowance(double managementAllowance) {
        this.managementAllowance = managementAllowance;
    }
}
