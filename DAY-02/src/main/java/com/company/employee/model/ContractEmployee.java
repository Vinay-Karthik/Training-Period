package com.company.employee.model;

public class ContractEmployee extends Employee {

    private int contractDurationMonths;

    public ContractEmployee(long employeeId, String name, String email, double annualSalary,
                            Department department, Designation designation, int contractDurationMonths) {
        super(employeeId, name, email, annualSalary, department, designation, EmploymentType.CONTRACT);
        setContractDurationMonths(contractDurationMonths);
    }

    public int getContractDurationMonths() {
        return contractDurationMonths;
    }

    private void setContractDurationMonths(int contractDurationMonths) {
        if (contractDurationMonths <= 0) {
            throw new IllegalArgumentException("Contract duration must be positive");
        }
        this.contractDurationMonths = contractDurationMonths;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Contract Duration (months): " + contractDurationMonths);
    }
}
