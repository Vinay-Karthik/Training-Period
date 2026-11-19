package com.company.payroll.model.interfaces;

/**
 * ISP: Interface Segregation Principle.
 * Only employees entitled to HRA and allowances implement this interface.
 */
public interface HasHra {
    double getBasicSalary();
    double getHra();
    double getAllowances();
}
