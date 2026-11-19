package com.company.payroll.strategy.salary;

import com.company.payroll.model.Employee;

/**
 * Strategy Pattern & Open/Closed Principle (OCP):
 * Abstraction for calculating gross salary. New employee salary types
 * can be added without modifying the core payroll processor.
 */
public interface SalaryStrategy {
    double calculateGross(Employee employee);
}
