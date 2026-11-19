package com.company.payroll.solid_bad;

/**
 * BAD DESIGN: Monolithic God Class violating SOLID:
 * 
 * 1. SRP: Handles gross pay, tax, deductions, database saving, and emailing all in one place.
 * 2. OCP: If-else ladder based on type. Adding a new employee type requires editing this code!
 * 3. LSP: Calls emp.getHraAllowance() assuming all BadEmployee subclasses support it. Crashes on BadContractEmployee!
 * 4. ISP: No small interfaces; clients are forced to depend on everything.
 * 5. DIP: Hardcoded tight coupling using 'new' for database and email.
 */
public class BadPayrollService {

    public double processPayroll(BadEmployee emp) {
        System.out.println("[BadPayroll] Processing for: " + emp.getName());

        // 1. LSP VIOLATION: Calling getHraAllowance() polymorphically on BadEmployee.
        // BadContractEmployee overrides this to throw an exception, crashing the service!
        double hra = emp.getHraAllowance();

        // 2. OCP VIOLATION: Hardcoded if-else. Cannot extend without modifying!
        double gross = 0.0;
        if ("PERMANENT".equalsIgnoreCase(emp.getType())) {
            gross = emp.getBasicSalary() + hra + emp.getAllowances();
        } else if ("CONTRACT".equalsIgnoreCase(emp.getType())) {
            gross = emp.getContractAmount();
        } else if ("SALES".equalsIgnoreCase(emp.getType())) {
            gross = emp.getBasicSalary() + (emp.getSalesAmount() * emp.getCommissionRate());
        } else if ("EXECUTIVE".equalsIgnoreCase(emp.getType())) {
            gross = emp.getBasicSalary() + emp.getBonus() + emp.getBenefits();
        } else {
            throw new IllegalArgumentException("Unknown employee type: " + emp.getType());
        }

        // 3. SRP VIOLATION: Tax calculation mixed directly with payroll
        double tax = 0.0;
        if ("OLD".equalsIgnoreCase(emp.getTaxRegime())) {
            tax = (gross > 50000) ? (gross - 50000) * 0.20 : 0.0;
        } else {
            tax = (gross > 60000) ? (gross - 60000) * 0.15 : 0.0;
        }

        // 4. SRP VIOLATION: Deductions mixed directly
        double pf = "PERMANENT".equalsIgnoreCase(emp.getType()) ? emp.getBasicSalary() * 0.12 : 0.0;
        double net = gross - tax - pf;

        // 5. DIP VIOLATION: Directly coupled to console / concrete I/O
        System.out.println("[BadDB] Saving record to database for: " + emp.getId());
        System.out.println("[BadEmail] Sending email to: " + emp.getEmail() + " Net Salary = $" + net);

        return net;
    }
}
