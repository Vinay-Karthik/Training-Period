package com.example.employeesearch.model;

public record Employee(
        String id,
        String name,
        Department department,
        Designation designation,
        EmploymentType employmentType,
        double salary
) {
    public static Employee createDefault() {
        return new Employee("EMP999", "Default Employee", Department.ENGINEERING, Designation.SOFTWARE_ENGINEER, EmploymentType.FULL_TIME, 500000.0);
    }
}
