package com.company.employee.model;

public class Manager extends Employee {

    private int teamSize;

    public Manager(long employeeId, String name, String email, double annualSalary,
                   Department department, Designation designation, int teamSize) {
        super(employeeId, name, email, annualSalary, department, designation, EmploymentType.MANAGER);
        setTeamSize(teamSize);
    }

    public int getTeamSize() {
        return teamSize;
    }

    private void setTeamSize(int teamSize) {
        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative");
        }
        this.teamSize = teamSize;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Team Size: " + teamSize);
    }
}
