package com.company.hr;

import com.company.hr.dto.PayrollResult;
import com.company.hr.model.*;
import com.company.hr.service.PayrollService;
import com.company.hr.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {

    private PayrollService payrollService;
    private TaxService taxService;

    @BeforeEach
    void setUp() {
        taxService = new TaxService();
        payrollService = new PayrollService(taxService);
    }

    @ParameterizedTest(name = "Salary: {0} => Expected Tax: {1}")
    @CsvSource({
            "250000.0, 0.0",
            "550000.0, 27500.0",
            "750000.0, 75000.0",
            "1200000.0, 240000.0"
    })
    @DisplayName("Tax service boundary calculations")
    void testTaxBoundaries(double salary, double expectedTax) {
        assertEquals(expectedTax, taxService.calculateAnnualTax(salary), 0.01);
    }

    @Test
    @DisplayName("Process payroll for single permanent employee")
    void testProcessEmployeePayroll() {
        Employee emp = new PermanentEmployee("EMP003", "Arjun", "arjun@company.com", Department.ENGINEERING, Designation.LEAD_ENGINEER, 1200000.0);
        PayrollResult result = payrollService.processEmployeePayroll(emp);

        assertEquals("EMP003", result.employeeId());
        assertEquals(1200000.0, result.annualGrossSalary());
        assertEquals(240000.0, result.annualTax());
        assertEquals(960000.0, result.annualNetSalary());
    }
}
