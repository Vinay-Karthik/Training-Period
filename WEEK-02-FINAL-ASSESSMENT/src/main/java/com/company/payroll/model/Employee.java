package com.company.payroll.model;

/**
 * Base Employee class.
 * LSP Compliant: Contains only common attributes shared by all employees.
 * Does not force contract-breaking methods on subclasses.
 */
public abstract class Employee {
    private final String id;
    private final String name;
    private final EmployeeType type;
    private final String department;
    private final String taxRegime; // "OLD" or "NEW"
    private final String email;

    public Employee(String id, String name, EmployeeType type, String department, String taxRegime, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.department = department;
        this.taxRegime = taxRegime;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public EmployeeType getType() { return type; }
    public String getDepartment() { return department; }
    public String getTaxRegime() { return taxRegime; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return name + " [" + id + ", " + type + ", " + department + "]";
    }
}
