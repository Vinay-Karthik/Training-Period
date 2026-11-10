package com.example.employeesearch.model;

import java.util.Objects;
import java.util.regex.Pattern;

public record Employee(
        String employeeId,
        String name,
        String email,
        double salary,
        Department department,
        Designation designation,
        EmploymentType employmentType
) implements Comparable<Employee> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Employee {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be null or blank.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        if (email == null || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email cannot be null or invalid: " + email);
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        Objects.requireNonNull(department, "Department cannot be null.");
        Objects.requireNonNull(designation, "Designation cannot be null.");
        Objects.requireNonNull(employmentType, "Employment type cannot be null.");
    }

    @Override
    public int compareTo(Employee other) {
        Objects.requireNonNull(other, "Cannot compare against null Employee");
        return this.employeeId.compareTo(other.employeeId);
    }
}
