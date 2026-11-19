package com.company.payroll.strategy.tax;

/**
 * Old Tax Regime Strategy:
 * - Up to 25,000: 0%
 * - 25,001 to 50,000: 10%
 * - Above 50,000: 20%
 */
public class OldTaxRegimeStrategy implements TaxStrategy {

    @Override
    public double calculateTax(double taxableAmount) {
        if (taxableAmount <= 25000) return 0.0;
        double tax = 0.0;
        if (taxableAmount > 50000) {
            tax += (taxableAmount - 50000) * 0.20;
            tax += 25000 * 0.10;
        } else {
            tax += (taxableAmount - 25000) * 0.10;
        }
        return tax;
    }
}
