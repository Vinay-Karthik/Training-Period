package com.company.employee.model;

public class PermanentEmployee extends Employee {

    private double annualBonus;

    public PermanentEmployee(long employeeId, String name, String email, double annualSalary,
                             Department department, Designation designation, double annualBonus) {
        super(employeeId, name, email, annualSalary, department, designation, EmploymentType.PERMANENT);
        setAnnualBonus(annualBonus);
    }

    public double getAnnualBonus() {
        return annualBonus;
    }

    private void setAnnualBonus(double annualBonus) {
        if (annualBonus < 0) {
            throw new IllegalArgumentException("Annual bonus cannot be negative");
        }
        this.annualBonus = annualBonus;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Annual Bonus: " + annualBonus);
    }
}
