package com.company.employee.model;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

    // final constant shared by all employees
    public static final String COMPANY_NAME = "TrueID Corp";

    private final long employeeId;
    private String name;
    private String email;
    private double annualSalary;
    private Department department;
    private Designation designation;
    private EmploymentType employmentType;

    public Employee(long employeeId, String name, String email, double annualSalary,
                    Department department, Designation designation, EmploymentType employmentType) {
        this.employeeId = employeeId;
        setName(name);
        setEmail(email);
        setAnnualSalary(annualSalary);
        setDepartment(department);
        setDesignation(designation);
        setEmploymentType(employmentType);
    }

    public final long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if (Objects.isNull(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    private void setAnnualSalary(double annualSalary) {
        if (annualSalary <= 0) {
            throw new IllegalArgumentException("Salary must be a positive number");
        }
        this.annualSalary = annualSalary;
    }

    public Department getDepartment() {
        return department;
    }

    private void setDepartment(Department department) {
        if (Objects.isNull(department)) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    private void setDesignation(Designation designation) {
        if (Objects.isNull(designation)) {
            throw new IllegalArgumentException("Designation cannot be null");
        }
        this.designation = designation;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    private void setEmploymentType(EmploymentType employmentType) {
        if (Objects.isNull(employmentType)) {
            throw new IllegalArgumentException("Employment type cannot be null");
        }
        this.employmentType = employmentType;
    }

    public void displayDetails() {
        System.out.println("ID: " + getEmployeeId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department.getName());
        System.out.println("Designation: " + designation.getTitle());
        System.out.println("Type: " + employmentType);
        System.out.println("Annual Salary: " + annualSalary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public int compareTo(Employee other) {
        return Long.compare(this.employeeId, other.employeeId);
    }

}
