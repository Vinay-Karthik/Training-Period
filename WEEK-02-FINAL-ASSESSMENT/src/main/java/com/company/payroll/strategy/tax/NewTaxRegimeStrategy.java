package com.company.payroll.strategy.tax;

/**
 * New Tax Regime Strategy:
 * - Up to 30,000: 0%
 * - 30,001 to 60,000: 5%
 * - Above 60,000: 15%
 */
public class NewTaxRegimeStrategy implements TaxStrategy {

    @Override
    public double calculateTax(double taxableAmount) {
        if (taxableAmount <= 30000) return 0.0;
        double tax = 0.0;
        if (taxableAmount > 60000) {
            tax += (taxableAmount - 60000) * 0.15;
            tax += 30000 * 0.05;
        } else {
            tax += (taxableAmount - 30000) * 0.05;
        }
        return tax;
    }
}
