package com.example.employeesearch.service;

import com.example.employeesearch.functional.PayrollFunctionalInterface;
import com.example.employeesearch.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EmployeeStreamService {

    // Module 1 – Lambda expressions and functional interfaces
    public void printAllEmployeeNames(List<Employee> employees) {
        employees.forEach(emp -> System.out.println(emp.name()));
    }

    public List<Employee> sortBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary))
                .toList();
    }

    public List<Employee> filterSalaryAbove75k(List<Employee> employees) {
        return employees.stream()
                .filter(emp -> emp.salary() > 75000.0)
                .toList();
    }

    public List<Employee> filterNamesStartingWithA(List<Employee> employees) {
        return employees.stream()
                .filter(emp -> emp.name().startsWith("A"))
                .toList();
    }

    // Module 3 – Method References
    public void demonstrateMethodReferences(List<Employee> employees) {
        // 1. Static method reference
        List<Double> roundedSalaries = employees.stream()
                .map(Employee::salary)
                .map(Math::round)
                .map(Long::doubleValue)
                .toList();

        // 2. Instance method reference of a particular object
        Consumer<Object> sysOut = System.out::println;

        // 3. Arbitrary object instance method reference
        List<String> names = employees.stream()
                .map(Employee::name)
                .toList();

        // 4. Constructor reference
        Supplier<StringBuilder> sbSupplier = StringBuilder::new;
    }

    // Module 4 – Stream API Fundamentals
    public List<Employee> filterSalaryAbove80k(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.salary() > 80000.0)
                .toList();
    }

    public List<String> extractEmployeeNames(List<Employee> employees) {
        return employees.stream()
                .map(Employee::name)
                .toList();
    }

    public List<Employee> sortEmployeesBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary))
                .toList();
    }

    public List<String> findUniqueDepartments(List<Employee> employees) {
        return employees.stream()
                .map(e -> e.department().name())
                .distinct()
                .toList();
    }

    public List<Employee> getTopFiveEmployees(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .limit(5)
                .toList();
    }

    public long countEmployees(List<Employee> employees) {
        return employees.size();
    }

    public Optional<Employee> findFirstMatching(List<Employee> employees, Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .findFirst();
    }

    public boolean anyEarnsAbove100k(List<Employee> employees) {
        return employees.stream().anyMatch(e -> e.salary() > 100000.0);
    }

    public boolean allSalariesPositive(List<Employee> employees) {
        return employees.stream().allMatch(e -> e.salary() > 0);
    }

    public boolean noNegativeSalaries(List<Employee> employees) {
        return employees.stream().noneMatch(e -> e.salary() < 0);
    }

    // Module 5 – map, flatMap and reduce
    public List<Employee> flattenDepartmentLists(List<List<Employee>> departmentLists) {
        return departmentLists.stream()
                .flatMap(List::stream)
                .toList();
    }

    public double calculateTotalPayrollExpenditure(List<Employee> employees) {
        return employees.stream()
                .map(Employee::salary)
                .reduce(0.0, Double::sum);
    }

    public Optional<Double> getHighestSalary(List<Employee> employees) {
        return employees.stream()
                .map(Employee::salary)
                .reduce(Double::max);
    }

    public Optional<Double> getLowestSalary(List<Employee> employees) {
        return employees.stream()
                .map(Employee::salary)
                .reduce(Double::min);
    }
}
