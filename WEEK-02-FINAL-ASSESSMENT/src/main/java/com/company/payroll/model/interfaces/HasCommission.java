package com.company.payroll.model.interfaces;

/**
 * ISP: Interface Segregation Principle.
 * Only sales roles with commissions implement this interface.
 */
public interface HasCommission {
    double getBaseSalary();
    double getSalesAmount();
    double getCommissionRate();
}
