package com.example.employeesearch.service;

import com.example.employeesearch.dto.DepartmentPayrollSummary;
import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentReportService {

    public Map<Department, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::department));
    }

    public Map<Department, Long> getEmployeeCountByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()
                ));
    }

    public Map<Department, Double> getTotalSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)
                ));
    }

    public Map<Department, Double> getAverageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));
    }

    public Map<Department, Optional<Employee>> getHighestSalaryEmployeeByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                ));
    }

    public Map<Department, Optional<Employee>> getLowestSalaryEmployeeByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.minBy(Comparator.comparingDouble(Employee::salary))
                ));
    }

    public Map<Department, Double> getTaxCollectionByDepartment(List<Employee> employees, TaxService taxService) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(e -> taxService.calculateAnnualTax(e.salary()))
                ));
    }

    public Map<Boolean, List<Employee>> partitionEmployeesByHighSalary(List<Employee> employees, double threshold) {
        return employees.stream()
                .collect(Collectors.partitioningBy(e -> e.salary() >= threshold));
    }

    public Map<Department, DoubleSummaryStatistics> getDepartmentSalaryStatistics(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summarizingDouble(Employee::salary)
                ));
    }

    public List<DepartmentPayrollSummary> generateDepartmentSummaries(List<Employee> employees, TaxService taxService) {
        Map<Department, DoubleSummaryStatistics> statsMap = getDepartmentSalaryStatistics(employees);
        Map<Department, Double> taxMap = getTaxCollectionByDepartment(employees, taxService);

        List<DepartmentPayrollSummary> summaries = new ArrayList<>();
        for (Department dept : Department.values()) {
            DoubleSummaryStatistics stats = statsMap.get(dept);
            if (stats != null && stats.getCount() > 0) {
                summaries.add(new DepartmentPayrollSummary(
                        dept,
                        stats.getCount(),
                        stats.getSum(),
                        stats.getAverage(),
                        stats.getMax(),
                        stats.getMin(),
                        taxMap.getOrDefault(dept, 0.0)
                ));
            }
        }
        return summaries;
    }
}
