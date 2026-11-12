package service;

import model.Employee;
import model.PayrollResult;

import java.util.Objects;

public class PayrollService {
    private static final double TAX_RATE = 0.20; // 20% Tax
    private static final double DEDUCTION_RATE = 0.05; // 5% Benefits/Deductions

    public PayrollResult calculatePayroll(Employee employee) {
        Objects.requireNonNull(employee, "Employee record cannot be null");
        
        try {
            double gross = employee.grossSalary();
            double tax = roundToCents(gross * TAX_RATE);
            double otherDeductions = roundToCents(gross * DEDUCTION_RATE);
            
            // Net Salary = Gross Salary - Tax - Other Deductions
            double net = roundToCents(gross - tax - otherDeductions);

            return PayrollResult.success(employee.id(), gross, tax, otherDeductions, net);
        } catch (Exception e) {
            return PayrollResult.failure(employee.id(), e.getMessage());
        }
    }

    private static double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
