package com.example.employeesearch.service;

import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.model.EmploymentType;

import java.util.*;

public class EmployeeGroupingService {

    public Map<Department, List<Employee>> groupByDepartment(List<Employee> employees) {
        Map<Department, List<Employee>> map = new HashMap<>();
        for (Employee emp : employees) {
            map.computeIfAbsent(emp.department(), k -> new ArrayList<>()).add(emp);
        }
        return map;
    }

    public Map<EmploymentType, List<Employee>> groupByEmploymentType(List<Employee> employees) {
        Map<EmploymentType, List<Employee>> map = new HashMap<>();
        for (Employee emp : employees) {
            map.computeIfAbsent(emp.employmentType(), k -> new ArrayList<>()).add(emp);
        }
        return map;
    }

    public Map<Department, Map<EmploymentType, List<Employee>>> groupNested(List<Employee> employees) {
        Map<Department, Map<EmploymentType, List<Employee>>> nestedMap = new HashMap<>();
        for (Employee emp : employees) {
            nestedMap
                    .computeIfAbsent(emp.department(), k -> new HashMap<>())
                    .computeIfAbsent(emp.employmentType(), k -> new ArrayList<>())
                    .add(emp);
        }
        return nestedMap;
    }

    public Set<String> findDuplicateEmployeeIds(List<Employee> employees) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (Employee emp : employees) {
            if (!seen.add(emp.employeeId())) {
                duplicates.add(emp.employeeId());
            }
        }
        return duplicates;
    }

    public Set<String> findDuplicateEmails(List<Employee> employees) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (Employee emp : employees) {
            String lowerEmail = emp.email().toLowerCase();
            if (!seen.add(lowerEmail)) {
                duplicates.add(lowerEmail);
            }
        }
        return duplicates;
    }

    public Map<Department, Employee> findHighestPaidEmployeeByDepartment(List<Employee> employees) {
        Map<Department, Employee> map = new HashMap<>();
        for (Employee emp : employees) {
            map.merge(emp.department(), emp, (existing, current) ->
                    current.salary() > existing.salary() ? current : existing
            );
        }
        return map;
    }
}
