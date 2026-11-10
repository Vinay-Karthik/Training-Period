package com.company.hr.repository;

import com.company.hr.exception.DuplicateEmployeeException;
import com.company.hr.exception.EmployeeNotFoundException;
import com.company.hr.model.Employee;

import java.util.*;


public class EmployeeRepository {

    // Single collection handles fast lookup (by ID) and insertion order tracking
    private final Map<String, Employee> employeeMap = new LinkedHashMap<>();

    public synchronized void save(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");

        if (employeeMap.containsKey(employee.getId())) {
            throw new DuplicateEmployeeException("Employee with ID '" + employee.getId() + "' already exists.");
        }

        boolean emailExists = employeeMap.values().stream()
                .anyMatch(e -> e.getEmail().equalsIgnoreCase(employee.getEmail()));
        if (emailExists) {
            throw new DuplicateEmployeeException("Employee with email '" + employee.getEmail() + "' already exists.");
        }

        employeeMap.put(employee.getId(), employee);
    }

    public Optional<Employee> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(employeeMap.get(id));
    }

    public synchronized boolean deleteById(String id) {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Cannot delete: Employee not found with ID: " + id);
        }
        employeeMap.remove(id);
        return true;
    }

    public List<Employee> findAll() {
        return List.copyOf(employeeMap.values());
    }

    public NavigableSet<Employee> getSortedEmployees() {
        TreeSet<Employee> sorted = new TreeSet<>(Comparator.comparing(Employee::getId));
        sorted.addAll(employeeMap.values());
        return Collections.unmodifiableNavigableSet(sorted);
    }

    public Set<String> findDuplicateIdsOrEmails(List<Employee> inputEmployees) {
        Set<String> duplicates = new HashSet<>();
        Set<String> seenIds = new HashSet<>();
        Set<String> seenEmails = new HashSet<>();

        for (Employee e : inputEmployees) {
            if (!seenIds.add(e.getId())) {
                duplicates.add("Duplicate ID: " + e.getId());
            }
            if (!seenEmails.add(e.getEmail().toLowerCase())) {
                duplicates.add("Duplicate Email: " + e.getEmail());
            }
        }
        return duplicates;
    }

    public <T extends Employee> void addAllEmployees(List<? extends T> newEmployees, List<? super T> destination) {
        for (T emp : newEmployees) {
            save(emp);
            destination.add(emp);
        }
    }

    public synchronized int removeEmployeesWithSalaryLessThan(double threshold) {
        int initialSize = employeeMap.size();
        employeeMap.values().removeIf(emp -> emp.calculateAnnualGrossSalary() < threshold);
        return initialSize - employeeMap.size();
    }

    public int count() {
        return employeeMap.size();
    }
}