package com.example.employeesearch;

import com.example.employeesearch.dto.DepartmentPayrollSummary;
import com.example.employeesearch.dto.PayrollResult;
import com.example.employeesearch.dto.SalaryDistribution;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.service.*;
import com.example.employeesearch.util.PayrollDataProvider;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("   EMPLOYEE PAYROLL PROCESSING ENGINE - DAY 4 ASSESSMENT");
        System.out.println("================================================================");

        List<Employee> employees = PayrollDataProvider.getSampleEmployees();
        TaxService taxService = new TaxService();
        PayrollService payrollService = new PayrollService(taxService);
        EmployeeStreamService streamService = new EmployeeStreamService();
        DepartmentReportService departmentReportService = new DepartmentReportService();
        SalaryAnalyticsService analyticsService = new SalaryAnalyticsService();

        // 1. Process Employee Payrolls (Module 7)
        System.out.println("\n--- Payroll Results ---");
        List<PayrollResult> payrollResults = payrollService.processPayroll(employees);
        System.out.printf("%-10s %-12s %-18s %-15s %-15s %-15s%n",
                "ID", "Employee", "Department", "Annual Gross", "Annual Tax", "Annual Net");
        System.out.println("-".repeat(85));
        for (PayrollResult r : payrollResults) {
            System.out.printf("%-10s %-12s %-18s ₹%-14.2f ₹%-14.2f ₹%-14.2f%n",
                    r.employeeId(), r.employeeName(), r.department(),
                    r.annualGrossSalary(), r.annualTax(), r.annualNetSalary());
        }

        // 2. Department-wise Payroll Summary (Module 6)
        System.out.println("\n--- Department-Wise Payroll Summary ---");
        List<DepartmentPayrollSummary> summaries = departmentReportService.generateDepartmentSummaries(employees, taxService);
        System.out.printf("%-20s %-12s %-20s %-20s %-15s%n",
                "Department", "Employees", "Total Annual Salary", "Average Salary", "Total Tax");
        System.out.println("-".repeat(90));
        for (DepartmentPayrollSummary s : summaries) {
            System.out.printf("%-20s %-12d ₹%-19.2f ₹%-19.2f ₹%-14.2f%n",
                    s.department(), s.employeeCount(), s.totalAnnualSalary(), s.averageAnnualSalary(), s.totalAnnualTax());
        }

        // 3. Organization Overview & Analytics
        System.out.println("\n--- Overall Organization Analytics ---");
        double totalPayroll = analyticsService.calculateTotalPayroll(employees);
        double avgSalary = analyticsService.calculateAverageSalary(employees);
        SalaryDistribution dist = analyticsService.calculateSalaryDistribution(employees);

        System.out.printf("Total Organization Payroll: ₹%.2f%n", totalPayroll);
        System.out.printf("Average Employee Salary:   ₹%.2f%n", avgSalary);
        System.out.println("Salary Distribution:");
        System.out.println("  • Below ₹5 Lakh:    " + dist.countBelow5Lakh() + " employees");
        System.out.println("  • ₹5 Lakh – ₹10 Lakh: " + dist.count5To10Lakh() + " employees");
        System.out.println("  • Above ₹10 Lakh:   " + dist.countAbove10Lakh() + " employees");

        // 4. Stream & Functional Programming Demonstration (Modules 1-5)
        System.out.println("\n--- Key Functional & Stream Demonstrations ---");
        System.out.println("Employees earning > ₹75,000:");
        streamService.filterSalaryAbove75k(employees).forEach(e -> System.out.println("  - " + e.name() + " (₹" + e.salary() + ")"));

        System.out.println("Unique Departments:");
        streamService.findUniqueDepartments(employees).forEach(d -> System.out.println("  - " + d));

        // 5. Parallel vs Sequential Benchmark (Module 9)
        System.out.println();
        List<Employee> largeDataset = PayrollDataProvider.generateLargeDataset(100_000);
        analyticsService.compareSequentialAndParallelExecution(largeDataset, taxService);
    }
}
