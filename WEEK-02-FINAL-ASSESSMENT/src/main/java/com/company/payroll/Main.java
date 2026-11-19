package com.company.payroll;

import com.company.payroll.model.*;
import com.company.payroll.notification.ConsoleNotificationService;
import com.company.payroll.repository.InMemoryEmployeeRepository;
import com.company.payroll.repository.InMemoryPayrollRepository;
import com.company.payroll.service.DeductionService;
import com.company.payroll.service.PayrollService;
import com.company.payroll.solid_bad.BadContractEmployee;
import com.company.payroll.solid_bad.BadEmployee;
import com.company.payroll.solid_bad.BadPayrollService;
import com.company.payroll.strategy.salary.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("              WEEK 2 FINAL ASSESSMENT: SOLID PAYROLL FRAMEWORK                  ");
        System.out.println("================================================================================\n");

        // -------------------------------------------------------------------------
        // PART 1: THE BAD PAYROLL DESIGN (SOLID VIOLATIONS)
        // -------------------------------------------------------------------------
        System.out.println(">>> 1. DEMONSTRATING BAD PAYROLL DESIGN (FLAWS & CRASHES) <<<");
        BadPayrollService badService = new BadPayrollService();

        BadEmployee badPermanent = new BadEmployee(
                "BAD-01", "Alice", "PERMANENT", 50000, 20000, 10000, 0, 0, 0, 0, 0, "OLD", "alice@corp.com"
        );
        badService.processPayroll(badPermanent);

        // LSP VIOLATION: Contract employee crashes because it doesn't support HRA
        System.out.println("\n--- Testing LSP Violation with BadContractEmployee ---");
        try {
            BadEmployee badContract = new BadContractEmployee("BAD-02", "Bob", 60000, "bob@corp.com");
            badService.processPayroll(badContract);
        } catch (UnsupportedOperationException e) {
            System.out.println("CRASH DETECTED (LSP Violation): " + e.getMessage());
            System.out.println("Reason: Subclass broke the parent contract by throwing an unexpected exception!");
        }

        // -------------------------------------------------------------------------
        // PART 2: THE REFACTORED SOLID DESIGN
        // -------------------------------------------------------------------------
        System.out.println("\n>>> 2. REFACTORED CLEAN PAYROLL FRAMEWORK (SOLID COMPLIANT) <<<");

        // DIP: Invert dependencies by injecting abstractions
        InMemoryEmployeeRepository empRepo = new InMemoryEmployeeRepository();
        InMemoryPayrollRepository payrollRepo = new InMemoryPayrollRepository();
        ConsoleNotificationService notificationService = new ConsoleNotificationService();
        DeductionService deductionService = new DeductionService();

        PayrollService payrollService = new PayrollService(
                empRepo, payrollRepo, notificationService, deductionService
        );

        // OCP: Register salary strategies
        payrollService.registerSalaryStrategy(EmployeeType.PERMANENT, new PermanentSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.CONTRACT, new ContractSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.SALES, new SalesSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.EXECUTIVE, new ExecutiveSalaryStrategy());

        // LSP & ISP: Create employees of different types
        Employee emp1 = new PermanentEmployee("EMP-01", "Dr. Rajesh", "Engineering", "OLD", "rajesh@corp.com", 60000, 24000, 16000);
        Employee emp2 = new ContractEmployee("EMP-02", "Anita Desai", "Operations", "NEW", "anita@corp.com", 75000);
        Employee emp3 = new SalesEmployee("EMP-03", "Vikram Mehta", "Sales", "NEW", "vikram@corp.com", 35000, 500000, 0.08);
        Employee emp4 = new ExecutiveEmployee("EMP-04", "Sunita Rao", "Executive", "OLD", "sunita@corp.com", 120000, 50000, 30000);

        empRepo.save(emp1);
        empRepo.save(emp2);
        empRepo.save(emp3);
        empRepo.save(emp4);

        System.out.println("\n--- Processing Monthly Payroll for All Employees ---");
        List<PayrollResult> results = payrollService.processAll();

        System.out.println("\n-------------------------------- FINAL PAYROLL SUMMARY --------------------------------");
        System.out.printf("%-10s | %-16s | %-10s | %-16s | %-14s | %-14s | %-16s%n",
                "EMP ID", "NAME", "TYPE", "GROSS SALARY", "DEDUCTIONS", "TAX", "NET SALARY");
        System.out.println("---------------------------------------------------------------------------------------");
        for (PayrollResult r : results) {
            System.out.printf("%-10s | %-16s | %-10s | $%,14.2f | $%,12.2f | $%,12.2f | $%,14.2f%n",
                    r.getEmployeeId(), r.getEmployeeName(), r.getEmployeeType(),
                    r.getGrossSalary(), r.getDeductions(), r.getTax(), r.getNetSalary());
        }
        System.out.println("---------------------------------------------------------------------------------------");

        // -------------------------------------------------------------------------
        // PART 3: OCP EXTENSION (Adding Freelancer without touching PayrollService!)
        // -------------------------------------------------------------------------
        System.out.println("\n>>> 3. DEMONSTRATING OPEN/CLOSED PRINCIPLE (OCP EXTENSION) <<<");
        System.out.println("Adding new FREELANCER employee type dynamically at runtime:");

        // Add strategy for Freelancer
        payrollService.registerSalaryStrategy(EmployeeType.FREELANCER, emp -> 45000.0);

        Employee freelancer = new Employee("EMP-05", "Pooja Hegde", EmployeeType.FREELANCER, "Design", "NEW", "pooja@freelance.org") {};
        PayrollResult freelancerResult = payrollService.processPayroll(freelancer);

        System.out.printf("%-10s | %-16s | %-10s | $%,14.2f | $%,12.2f | $%,12.2f | $%,14.2f%n",
                freelancerResult.getEmployeeId(), freelancerResult.getEmployeeName(), freelancerResult.getEmployeeType(),
                freelancerResult.getGrossSalary(), freelancerResult.getDeductions(), freelancerResult.getTax(), freelancerResult.getNetSalary());

        System.out.println("\n================================================================================");
        System.out.println("  SUCCESS: ALL 5 SOLID PRINCIPLES DEMONSTRATED CLEARLY AND SIMPLY!              ");
        System.out.println("================================================================================");
    }
}
