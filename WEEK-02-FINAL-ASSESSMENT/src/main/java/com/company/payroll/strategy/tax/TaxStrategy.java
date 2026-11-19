package com.company.payroll.strategy.tax;

/**
 * Strategy Pattern & Open/Closed Principle (OCP):
 * Abstraction for tax computation across Old and New regimes.
 */
public interface TaxStrategy {
    double calculateTax(double taxableAmount);
}
