package com.example.employeesearch.service;

import com.example.employeesearch.dto.SalaryDistribution;
import com.example.employeesearch.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryAnalyticsService {

    public double calculateTotalPayroll(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .sum();
    }

    public double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);
    }

    public SalaryDistribution calculateSalaryDistribution(List<Employee> employees) {
        long below5L = employees.stream().filter(e -> e.salary() < 500000.0).count();
        long between5And10L = employees.stream().filter(e -> e.salary() >= 500000.0 && e.salary() <= 1000000.0).count();
        long above10L = employees.stream().filter(e -> e.salary() > 1000000.0).count();

        return new SalaryDistribution(below5L, between5And10L, above10L);
    }

    // Module 9: Parallel Streams benchmarking
    public void compareSequentialAndParallelExecution(List<Employee> employees, TaxService taxService) {
        System.out.println("=== Parallel vs Sequential Stream Comparison ===");
        System.out.println("Dataset size: " + employees.size() + " records");

        // Warmup
        for (int i = 0; i < 5; i++) {
            employees.stream().mapToDouble(e -> taxService.calculateAnnualTax(e.salary())).sum();
            employees.parallelStream().mapToDouble(e -> taxService.calculateAnnualTax(e.salary())).sum();
        }

        // Sequential
        long startTimeSeq = System.nanoTime();
        double seqTotalTax = employees.stream()
                .mapToDouble(e -> taxService.calculateAnnualTax(e.salary()))
                .sum();
        long endTimeSeq = System.nanoTime();
        long seqDurationMs = (endTimeSeq - startTimeSeq) / 1_000_000;

        // Parallel
        long startTimePar = System.nanoTime();
        double parTotalTax = employees.parallelStream()
                .mapToDouble(e -> taxService.calculateAnnualTax(e.salary()))
                .sum();
        long endTimePar = System.nanoTime();
        long parDurationMs = (endTimePar - startTimePar) / 1_000_000;

        System.out.println("Sequential Stream Total Tax: ₹" + String.format("%.2f", seqTotalTax) + " | Time: " + seqDurationMs + " ms");
        System.out.println("Parallel Stream Total Tax:   ₹" + String.format("%.2f", parTotalTax) + " | Time: " + parDurationMs + " ms");
        System.out.println("Result Consistency Match: " + (Double.compare(seqTotalTax, parTotalTax) == 0));
    }
}
