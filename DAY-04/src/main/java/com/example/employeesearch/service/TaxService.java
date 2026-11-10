package com.example.employeesearch.service;

public class TaxService {
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
