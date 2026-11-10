package com.example.employeesearch.functional;

@FunctionalInterface
public interface TaxCalculator {
    double calculateTax(double salary);
}
