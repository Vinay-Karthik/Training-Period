package com.example.employeesearch;

import com.example.employeesearch.dto.PayrollResult;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.service.PayrollService;
import com.example.employeesearch.service.TaxService;
import com.example.employeesearch.util.PayrollDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {

    private PayrollService payrollService;
    private List<Employee> sampleEmployees;
    private TaxService taxService;

    @BeforeEach
    void setUp() {
        payrollService = new PayrollService(taxService = new TaxService());
        sampleEmployees = PayrollDataProvider.getSampleEmployees();
    }

    @Test
    @DisplayName("Process single employee payroll correctly")
    void testProcessEmployee() {
        Employee arjun = sampleEmployees.stream()
                .filter(e -> e.id().equals("EMP003"))
                .findFirst()
                .orElseThrow();

        PayrollResult result = payrollService.processEmployee(arjun);

        assertEquals("EMP003", result.employeeId());
        assertEquals("Arjun", result.employeeName());
        assertEquals(1200000.0, result.annualGrossSalary());
        assertEquals(240000.0, result.annualTax());
        assertEquals(960000.0, result.annualNetSalary());
    }

    @Test
    @DisplayName("Process empty employee list returns empty payroll list")
    void testEmptyEmployeeList() {
        List<PayrollResult> results = payrollService.processPayroll(Collections.emptyList());
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Optional lookup handles existing and missing employee IDs")
    void testOptionalLookup() {
        assertTrue(payrollService.findEmployeeById(sampleEmployees, "EMP001").isPresent());
        assertTrue(payrollService.findEmployeeById(sampleEmployees, "EMP999").isEmpty());

        Employee defaultEmp = payrollService.getEmployeeOrSupplier(sampleEmployees, "EMP999");
        assertEquals("EMP999", defaultEmp.id());

        assertThrows(IllegalArgumentException.class, () ->
                payrollService.getRequiredEmployee(sampleEmployees, "NON_EXISTENT")
        );
    }
}
