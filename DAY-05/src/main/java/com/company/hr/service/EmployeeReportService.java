package com.company.hr.service;

import com.company.hr.dto.DepartmentPayrollSummary;
import com.company.hr.dto.SalaryDistribution;
import com.company.hr.model.Department;
import com.company.hr.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeReportService {

    private final TaxService taxService;

    public EmployeeReportService(TaxService taxService) {
        this.taxService = taxService;
    }

    public List<DepartmentPayrollSummary> generateDepartmentSummaries(List<Employee> employees) {
        Map<Department, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        List<DepartmentPayrollSummary> summaries = new ArrayList<>();
        for (Department dept : Department.values()) {
            List<Employee> deptEmps = grouped.getOrDefault(dept, List.of());
            if (!deptEmps.isEmpty()) {
                DoubleSummaryStatistics stats = deptEmps.stream()
                        .collect(Collectors.summarizingDouble(Employee::calculateAnnualGrossSalary));

                double totalTax = deptEmps.stream()
                        .mapToDouble(e -> taxService.calculateAnnualTax(e.calculateAnnualGrossSalary()))
                        .sum();

                summaries.add(new DepartmentPayrollSummary(
                        dept,
                        stats.getCount(),
                        stats.getSum(),
                        stats.getAverage(),
                        stats.getMax(),
                        stats.getMin(),
                        totalTax
                ));
            }
        }
        return summaries;
    }

    public SalaryDistribution calculateSalaryDistribution(List<Employee> employees) {
        long below5L = employees.stream().filter(e -> e.calculateAnnualGrossSalary() < 500000.0).count();
        long between5And10L = employees.stream().filter(e -> {
            double sal = e.calculateAnnualGrossSalary();
            return sal >= 500000.0 && sal <= 1000000.0;
        }).count();
        long above10L = employees.stream().filter(e -> e.calculateAnnualGrossSalary() > 1000000.0).count();

        return new SalaryDistribution(below5L, between5And10L, above10L);
    }

    public void compareSequentialAndParallelExecution(List<Employee> employees) {
        System.out.println(" === Sequential vs Parallel Stream Benchmark ===");
        System.out.println("Processing dataset size: " + employees.size() + " records");

        // Warmup
        for (int i = 0; i < 5; i++) {
            employees.stream().mapToDouble(e -> taxService.calculateAnnualTax(e.calculateAnnualGrossSalary())).sum();
            employees.parallelStream().mapToDouble(e -> taxService.calculateAnnualTax(e.calculateAnnualGrossSalary())).sum();
        }

        // Sequential
        long startSeq = System.nanoTime();
        double seqSum = employees.stream()
                .mapToDouble(e -> taxService.calculateAnnualTax(e.calculateAnnualGrossSalary()))
                .sum();
        long endSeq = System.nanoTime();
        long seqTimeMs = (endSeq - startSeq) / 1_000_000;

        // Parallel
        long startPar = System.nanoTime();
        double parSum = employees.parallelStream()
                .mapToDouble(e -> taxService.calculateAnnualTax(e.calculateAnnualGrossSalary()))
                .sum();
        long endPar = System.nanoTime();
        long parTimeMs = (endPar - startPar) / 1_000_000;

        System.out.println("Sequential Stream Total Tax: ₹" + String.format("%.2f", seqSum) + " | Time: " + seqTimeMs + " ms");
        System.out.println("Parallel Stream Total Tax:   ₹" + String.format("%.2f", parSum) + " | Time: " + parTimeMs + " ms");
        System.out.println("Consistency Verified (Sequential == Parallel): " + (Double.compare(seqSum, parSum) == 0));
    }
}
