package com.company.hr;

import com.company.hr.model.*;
import com.company.hr.service.EmployeeSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSearchServiceTest {

    private EmployeeSearchService searchService;
    private List<Employee> sampleList;

    @BeforeEach
    void setUp() {
        searchService = new EmployeeSearchService();
        sampleList = List.of(
                new PermanentEmployee("EMP001", "Rahul", "rahul@company.com", Department.ENGINEERING, Designation.SENIOR_ENGINEER, 750000.0),
                new PermanentEmployee("EMP002", "Priya", "priya@company.com", Department.HUMAN_RESOURCES, Designation.HR_MANAGER, 950000.0),
                new ContractEmployee("EMP004", "Sneha", "sneha@company.com", Department.FINANCE, Designation.FINANCIAL_ANALYST, 550000.0, 12)
        );
    }

    @Test
    @DisplayName("Search employees by partial name match")
    void testSearchByName() {
        List<Employee> results = searchService.searchByName(sampleList, "rah");
        assertEquals(1, results.size());
        assertEquals("Rahul", results.get(0).getName());
    }

    @Test
    @DisplayName("Filter employees by department")
    void testFilterByDepartment() {
        List<Employee> engineering = searchService.filterByDepartment(sampleList, Department.ENGINEERING);
        assertEquals(1, engineering.size());
        assertEquals("EMP001", engineering.get(0).getId());
    }
}
