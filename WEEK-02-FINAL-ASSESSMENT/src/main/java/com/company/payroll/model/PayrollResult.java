package com.company.payroll.model;

/**
 * Encapsulates the finalized payroll outcome for an employee.
 */
public class PayrollResult {
    private final String employeeId;
    private final String employeeName;
    private final EmployeeType employeeType;
    private final double grossSalary;
    private final double deductions;
    private final double tax;
    private final double netSalary;

    public PayrollResult(String employeeId, String employeeName, EmployeeType employeeType,
                         double grossSalary, double deductions, double tax, double netSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeType = employeeType;
        this.grossSalary = grossSalary;
        this.deductions = deductions;
        this.tax = tax;
        this.netSalary = netSalary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public EmployeeType getEmployeeType() { return employeeType; }
    public double getGrossSalary() { return grossSalary; }
    public double getDeductions() { return deductions; }
    public double getTax() { return tax; }
    public double getNetSalary() { return netSalary; }

    @Override
    public String toString() {
        return String.format("%-10s | %-16s | %-10s | Gross: $%,9.2f | Ded: $%,7.2f | Tax: $%,7.2f | Net: $%,9.2f",
                employeeId, employeeName, employeeType, grossSalary, deductions, tax, netSalary);
    }
}
