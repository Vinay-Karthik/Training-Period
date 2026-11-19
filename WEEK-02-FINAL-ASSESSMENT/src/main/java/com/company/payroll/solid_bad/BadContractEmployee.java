package com.company.payroll.solid_bad;

/**
 * BAD DESIGN: LSP Violation.
 * A contract employee is forced to inherit BadEmployee, but does not have HRA.
 * Overriding getHraAllowance() to throw an exception breaks polymorphism!
 */
public class BadContractEmployee extends BadEmployee {

    public BadContractEmployee(String id, String name, double contractAmount, String email) {
        super(id, name, "CONTRACT", 0, 0, 0, contractAmount, 0, 0, 0, 0, "NEW", email);
    }

    @Override
    public double getHraAllowance() {
        // BREAKS LSP: Client calling getHraAllowance() on BadEmployee reference will crash!
        throw new UnsupportedOperationException("Contract employees do NOT have HRA!");
    }
}
