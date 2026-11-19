package com.company.payroll.repository;

import com.company.payroll.model.PayrollResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete in-memory store for payroll results.
 */
public class InMemoryPayrollRepository implements PayrollRepository {
    private final List<PayrollResult> records = new ArrayList<>();

    @Override
    public void save(PayrollResult result) {
        records.add(result);
    }

    @Override
    public List<PayrollResult> findAll() {
        return new ArrayList<>(records);
    }
}
