package com.company.payroll.model;

/**
 * Contract Employee: Fixed contract amount.
 * LSP Compliant: Does not inherit or throw exceptions on HRA or benefits.
 */
public class ContractEmployee extends Employee {
    private final double contractAmount;

    public ContractEmployee(String id, String name, String department, String taxRegime, 
                            String email, double contractAmount) {
        super(id, name, EmployeeType.CONTRACT, department, taxRegime, email);
        this.contractAmount = contractAmount;
    }

    public double getContractAmount() {
        return contractAmount;
    }
}
