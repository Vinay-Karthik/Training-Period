package com.company.hr;

import com.company.hr.exception.DuplicateEmployeeException;
import com.company.hr.exception.EmployeeNotFoundException;
import com.company.hr.model.*;
import com.company.hr.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new EmployeeRepository();
    }

    @Test
    @DisplayName("Save and retrieve employee by ID")
    void testSaveAndFind() {
        Employee emp = new PermanentEmployee("EMP100", "Test User", "test@company.com", Department.ENGINEERING, Designation.SOFTWARE_ENGINEER, 500000.0);
        repository.save(emp);

        assertTrue(repository.findById("EMP100").isPresent());
        assertEquals("Test User", repository.findById("EMP100").get().getName());
    }

    @Test
    @DisplayName("Save duplicate ID throws DuplicateEmployeeException")
    void testDuplicateSaveThrowsException() {
        Employee emp1 = new PermanentEmployee("EMP100", "User 1", "u1@company.com", Department.ENGINEERING, Designation.SOFTWARE_ENGINEER, 500000.0);
        Employee emp2 = new PermanentEmployee("EMP100", "User 2", "u2@company.com", Department.FINANCE, Designation.FINANCIAL_ANALYST, 600000.0);

        repository.save(emp1);
        assertThrows(DuplicateEmployeeException.class, () -> repository.save(emp2));
    }

    @Test
    @DisplayName("Delete missing employee throws EmployeeNotFoundException")
    void testDeleteMissingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> repository.deleteById("NON_EXISTENT"));
    }
}
