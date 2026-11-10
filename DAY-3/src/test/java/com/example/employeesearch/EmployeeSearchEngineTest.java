package com.example.employeesearch;

import com.example.employeesearch.model.*;
import com.example.employeesearch.repository.*;
import com.example.employeesearch.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSearchEngineTest {

    private EmployeeRepository repository;
    private EmployeeSearchService searchService;
    private EmployeeSortingService sortingService;
    private EmployeeGroupingService groupingService;
    private EmployeeAnalyticsService analyticsService;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;

    @BeforeEach
    void setUp() {
        repository = new EmployeeRepository();
        searchService = new EmployeeSearchService();
        sortingService = new EmployeeSortingService();
        groupingService = new EmployeeGroupingService();
        analyticsService = new EmployeeAnalyticsService();

        emp1 = new Employee("EMP001", "Rahul", "rahul@company.com", 75000, Department.ENGINEERING, Designation.SENIOR_SOFTWARE_ENGINEER, EmploymentType.PERMANENT);
        emp2 = new Employee("EMP002", "Priya", "priya@company.com", 95000, Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.PERMANENT);
        emp3 = new Employee("EMP003", "Arjun", "arjun@company.com", 120000, Department.ENGINEERING, Designation.ENGINEERING_MANAGER, EmploymentType.PERMANENT);

        repository.addEmployee(emp1);
        repository.addEmployee(emp2);
        repository.addEmployee(emp3);
    }

    @Test
    @DisplayName("Module 1: Employee record validation & natural sorting")
    void testEmployeeRecordValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                new Employee("", "John", "john@test.com", 50000, Department.FINANCE, Designation.ACCOUNTANT, EmploymentType.PERMANENT)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Employee("EMP100", "John", "invalid-email", 50000, Department.FINANCE, Designation.ACCOUNTANT, EmploymentType.PERMANENT)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Employee("EMP100", "John", "john@test.com", -10.0, Department.FINANCE, Designation.ACCOUNTANT, EmploymentType.PERMANENT)
        );

        assertTrue(emp1.compareTo(emp2) < 0);
    }

    @Test
    @DisplayName("Module 2: Repository Operations & Duplicate prevention")
    void testRepositoryOperations() {
        assertFalse(repository.addEmployee(emp1)); // Duplicate rejection
        assertEquals(3, repository.count());

        Optional<Employee> found = repository.findById("EMP001");
        assertTrue(found.isPresent());
        assertEquals("Rahul", found.get().name());

        assertTrue(repository.deleteEmployee("EMP001"));
        assertFalse(repository.existsById("EMP001"));
        assertEquals(2, repository.count());
    }

    @Test
    @DisplayName("Module 3: Generic Filtering")
    void testGenericFiltering() {
        List<Employee> eng = searchService.searchByDepartment(repository.findAll(), Department.ENGINEERING);
        assertEquals(2, eng.size());

        List<Employee> highPaid = searchService.searchByMinimumSalary(repository.findAll(), 90000);
        assertEquals(2, highPaid.size());
    }

    @Test
    @DisplayName("Module 4: Sorting")
    void testSorting() {
        List<Employee> sortedBySalaryDesc = sortingService.sortBySalaryDescending(repository.findAll());
        assertEquals("Arjun", sortedBySalaryDesc.get(0).name());
        assertEquals("Rahul", sortedBySalaryDesc.get(2).name());
    }

    @Test
    @DisplayName("Module 5: Grouping & Duplicate Detection")
    void testGroupingAndDuplicates() {
        Map<Department, List<Employee>> map = groupingService.groupByDepartment(repository.findAll());
        assertEquals(2, map.get(Department.ENGINEERING).size());

        List<Employee> listWithDuplicates = List.of(
                emp1,
                new Employee("EMP001", "Rahul Dup", "RAHUL@company.com", 75000, Department.ENGINEERING, Designation.SENIOR_SOFTWARE_ENGINEER, EmploymentType.PERMANENT)
        );
        assertEquals(1, groupingService.findDuplicateEmployeeIds(listWithDuplicates).size());
        assertEquals(1, groupingService.findDuplicateEmails(listWithDuplicates).size());
    }

    @Test
    @DisplayName("Module 6: Analytics")
    void testAnalytics() {
        assertEquals(290000.0, analyticsService.calculateTotalSalary(repository.findAll()));
        assertEquals(96666.66666666667, analyticsService.calculateAverageSalary(repository.findAll()), 0.01);
    }

    @Test
    @DisplayName("Module 7: Concurrent Repository Test")
    void testConcurrentRepository() throws InterruptedException {
        ConcurrentEmployeeRepository concRepo = new ConcurrentEmployeeRepository();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successCounter = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                if (concRepo.addEmployee(emp1)) {
                    successCounter.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));

        assertEquals(1, successCounter.get());
        assertEquals(1, concRepo.count());
    }
}
