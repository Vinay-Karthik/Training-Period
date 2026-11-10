package com.company.hr;

import com.company.hr.exception.InvalidEmployeeDataException;
import com.company.hr.model.*;
import com.company.hr.repository.EmployeeRepository;
import com.company.hr.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Test
    @DisplayName("Register employee with valid input successfully")
    void testRegisterEmployeeValid() {
        employeeService.registerEmployee("EMP001", "Rahul", "rahul@company.com", Department.ENGINEERING, Designation.SENIOR_ENGINEER, EmploymentType.PERMANENT, 750000.0);
        Employee emp = employeeService.getEmployeeById("EMP001");
        assertNotNull(emp);
        assertEquals("Rahul", emp.getName());
    }

    @Test
    @DisplayName("Register employee with invalid email throws InvalidEmployeeDataException")
    void testInvalidEmailThrowsException() {
        assertThrows(InvalidEmployeeDataException.class, () ->
                employeeService.registerEmployee("EMP002", "Priya", "invalid-email", Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.PERMANENT, 950000.0)
        );
    }

    @Test
    @DisplayName("Register employee with negative salary throws InvalidEmployeeDataException")
    void testNegativeSalaryThrowsException() {
        assertThrows(InvalidEmployeeDataException.class, () ->
                employeeService.registerEmployee("EMP003", "Arjun", "arjun@company.com", Department.ENGINEERING, Designation.LEAD_ENGINEER, EmploymentType.PERMANENT, -5000.0)
        );
    }
}
