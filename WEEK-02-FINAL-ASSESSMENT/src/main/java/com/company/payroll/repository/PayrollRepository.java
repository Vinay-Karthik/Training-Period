package com.company.payroll.repository;

import com.company.payroll.model.PayrollResult;
import java.util.List;

/**
 * DIP: Abstraction for payroll persistence.
 */
public interface PayrollRepository {
    void save(PayrollResult result);
    List<PayrollResult> findAll();
}
