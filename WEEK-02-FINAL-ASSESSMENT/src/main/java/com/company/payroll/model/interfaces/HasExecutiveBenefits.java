package com.company.payroll.model.interfaces;

/**
 * ISP: Interface Segregation Principle.
 * Only executives entitled to executive bonus and benefits implement this interface.
 */
public interface HasExecutiveBenefits {
    double getFixedSalary();
    double getBonus();
    double getBenefits();
}
