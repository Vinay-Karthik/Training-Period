package com.company.hr.model;

public non-sealed class ContractEmployee extends Employee {

    private int contractDurationMonths;

    public ContractEmployee(String id, String name, String email, Department department, Designation designation, double annualSalary, int contractDurationMonths) {
        super(id, name, email, department, designation, EmploymentType.CONTRACT, annualSalary);
        this.contractDurationMonths = contractDurationMonths;
    }

    @Override
    public double calculateAnnualGrossSalary() {
        // Contract employees receive direct fixed base salary without extra corporate perks
        return getBaseSalary();
    }

    public int getContractDurationMonths() {
        return contractDurationMonths;
    }

    public void setContractDurationMonths(int contractDurationMonths) {
        this.contractDurationMonths = contractDurationMonths;
    }
}
