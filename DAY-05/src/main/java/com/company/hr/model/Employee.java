package com.company.hr.model;

import java.util.Objects;

/**
 * Sealed base abstract class for Employee hierarchy (Day 1 & Day 2 OOP requirements).
 */
public abstract sealed class Employee implements Comparable<Employee>
        permits PermanentEmployee, ContractEmployee, Manager {

    private final String id;
    protected double baseSalary;
    private String name;
    private String email;
    private Department department;
    private Designation designation;
    private EmploymentType employmentType;

    public Employee(String id, String name, String email, Department department, Designation designation, EmploymentType employmentType, double baseSalary) {
        this.id = Objects.requireNonNull(id, "Employee ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.department = Objects.requireNonNull(department, "Department cannot be null");
        this.designation = Objects.requireNonNull(designation, "Designation cannot be null");
        this.employmentType = Objects.requireNonNull(employmentType, "Employment type cannot be null");
        this.baseSalary = baseSalary;
    }

    public abstract double calculateAnnualGrossSalary();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Employee o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return String.format("Employee[ID='%s', Name='%s', Dept=%s, Desg=%s, Type=%s, AnnualSalary=₹%.2f]",
                id, name, department, designation, employmentType, calculateAnnualGrossSalary());
    }
}
