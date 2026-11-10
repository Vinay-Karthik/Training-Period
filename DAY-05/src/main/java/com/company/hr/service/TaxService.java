package com.company.hr.service;

public class TaxService {

    /**
     * Calculates annual tax based on simplified assessment rules:
     * Up to 3,00,000 -> 0%
     * 3,00,001 - 6,00,000 -> 5%
     * 6,00,001 - 10,00,000 -> 10%
     * Above 10,00,000 -> 20%
     * Applicable rate is applied to the entire annual salary.
     */
    public double calculateAnnualTax(double annualSalary) {
        if (annualSalary <= 0) {
            return 0.0;
        }
        if (annualSalary <= 300000.0) {
            return 0.0;
        } else if (annualSalary <= 600000.0) {
            return annualSalary * 0.05;
        } else if (annualSalary <= 1000000.0) {
            return annualSalary * 0.10;
        } else {
            return annualSalary * 0.20;
        }
    }

    public double calculateMonthlyTax(double annualSalary) {
        return calculateAnnualTax(annualSalary) / 12.0;
    }

    public double calculateAnnualNetSalary(double annualSalary) {
        return annualSalary - calculateAnnualTax(annualSalary);
    }

    public double calculateMonthlyNetSalary(double annualSalary) {
        return calculateAnnualNetSalary(annualSalary) / 12.0;
    }
}
