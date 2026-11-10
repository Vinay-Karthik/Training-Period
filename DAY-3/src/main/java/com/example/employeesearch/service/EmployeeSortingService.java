package com.example.employeesearch.service;

import com.example.employeesearch.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeSortingService {

    public <T> List<T> sort(List<T> items, Comparator<? super T> comparator) {
        List<T> sortedList = new ArrayList<>(items);
        sortedList.sort(comparator);
        return List.copyOf(sortedList);
    }

    public List<Employee> sortById(List<Employee> employees) {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(null); // uses Comparable natural ordering
        return List.copyOf(sortedList);
    }

    public List<Employee> sortByName(List<Employee> employees) {
        return sort(employees, Comparator.comparing(Employee::name, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Employee> sortBySalaryAscending(List<Employee> employees) {
        return sort(employees, Comparator.comparingDouble(Employee::salary));
    }

    public List<Employee> sortBySalaryDescending(List<Employee> employees) {
        return sort(employees, Comparator.comparingDouble(Employee::salary).reversed());
    }

    public List<Employee> sortByDepartment(List<Employee> employees) {
        return sort(employees, Comparator.comparing(Employee::department));
    }

    public List<Employee> sortByDesignation(List<Employee> employees) {
        return sort(employees, Comparator.comparing(Employee::designation));
    }

    public List<Employee> sortByDepartmentAscAndSalaryDesc(List<Employee> employees) {
        Comparator<Employee> comp = Comparator.comparing(Employee::department)
                .thenComparing(Comparator.comparingDouble(Employee::salary).reversed())
                .thenComparing(Employee::name);
        return sort(employees, comp);
    }

    public List<Employee> sortBySalaryDescAndIdAsc(List<Employee> employees) {
        Comparator<Employee> comp = Comparator.comparingDouble(Employee::salary).reversed()
                .thenComparing(Employee::employeeId);
        return sort(employees, comp);
    }

    public List<Employee> sortByEmploymentTypeAscAndNameAsc(List<Employee> employees) {
        Comparator<Employee> comp = Comparator.comparing(Employee::employmentType)
                .thenComparing(Employee::name, String.CASE_INSENSITIVE_ORDER);
        return sort(employees, comp);
    }
}
