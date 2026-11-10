package com.company.employee.service;

import com.company.employee.model.Employee;

import java.util.Comparator;

public final class EmployeeComparators {

    public static final Comparator<Employee> BY_SALARY =
            Comparator.comparingDouble(Employee::getAnnualSalary)
                    .thenComparing(Employee::getEmployeeId);
    public static final Comparator<Employee> BY_NAME =
            Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(Employee::getEmployeeId);

    private EmployeeComparators() {
    }
}
