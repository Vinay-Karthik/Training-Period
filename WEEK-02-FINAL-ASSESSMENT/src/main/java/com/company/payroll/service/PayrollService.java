package com.company.payroll.service;

import com.company.payroll.model.Employee;
import com.company.payroll.model.EmployeeType;
import com.company.payroll.model.PayrollResult;
import com.company.payroll.notification.NotificationService;
import com.company.payroll.repository.EmployeeRepository;
import com.company.payroll.repository.PayrollRepository;
import com.company.payroll.strategy.salary.SalaryStrategy;
import com.company.payroll.strategy.tax.NewTaxRegimeStrategy;
import com.company.payroll.strategy.tax.OldTaxRegimeStrategy;
import com.company.payroll.strategy.tax.TaxStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REFACTORED PAYROLL SERVICE:
 * Demonstrates SOLID Principles cleanly:
 * 
 * - SRP: Orchestrates only. Calculation, persistence, and notifications are delegated.
 * - OCP: New salary strategies can be added via registerSalaryStrategy() without editing this class.
 * - LSP: Accepts any Employee subclass safely without type errors or broken contracts.
 * - DIP: Injects repository, notification, and deduction abstractions via constructor.
 */
public class PayrollService {
    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;
    private final NotificationService notificationService;
    private final DeductionService deductionService;

    // Strategies mapped by employee type (OCP)
    private final Map<EmployeeType, SalaryStrategy> salaryStrategies = new HashMap<>();
    private final TaxStrategy oldTaxStrategy = new OldTaxRegimeStrategy();
    private final TaxStrategy newTaxStrategy = new NewTaxRegimeStrategy();

    public PayrollService(EmployeeRepository employeeRepository,
                          PayrollRepository payrollRepository,
                          NotificationService notificationService,
                          DeductionService deductionService) {
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
        this.notificationService = notificationService;
        this.deductionService = deductionService;
    }

    /**
     * OCP: Register new salary strategy at runtime!
     */
    public void registerSalaryStrategy(EmployeeType type, SalaryStrategy strategy) {
        salaryStrategies.put(type, strategy);
    }

    /**
     * Workflow 1: Process payroll for a single employee.
     */
    public PayrollResult processPayroll(Employee emp) {
        // 1. Calculate Gross Salary using appropriate strategy (Strategy Pattern & OCP)
        SalaryStrategy salaryStrategy = salaryStrategies.get(emp.getType());
        if (salaryStrategy == null) {
            throw new IllegalArgumentException("No salary strategy for type: " + emp.getType());
        }
        double gross = salaryStrategy.calculateGross(emp);

        // 2. Calculate Deductions (SRP)
        double deductions = deductionService.calculateDeductions(emp, gross);

        // 3. Calculate Tax based on chosen tax regime (Strategy Pattern & OCP)
        TaxStrategy taxStrategy = "OLD".equalsIgnoreCase(emp.getTaxRegime()) ? oldTaxStrategy : newTaxStrategy;
        double taxableIncome = Math.max(0.0, gross - deductions);
        double tax = taxStrategy.calculateTax(taxableIncome);

        // 4. Calculate Net Take-Home Pay
        double net = Math.max(0.0, gross - deductions - tax);

        // 5. Create Result
        PayrollResult result = new PayrollResult(
                emp.getId(), emp.getName(), emp.getType(), gross, deductions, tax, net
        );

        // 6. Save Result (DIP)
        payrollRepository.save(result);

        // 7. Send Notification (DIP)
        notificationService.notifyPayroll(emp, result);

        return result;
    }

    /**
     * Batch process all employees in repository.
     */
    public List<PayrollResult> processAll() {
        List<PayrollResult> results = new ArrayList<>();
        for (Employee emp : employeeRepository.findAll()) {
            results.add(processPayroll(emp));
        }
        return results;
    }
}
