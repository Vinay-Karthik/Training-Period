package com.example.employeesearch;

import com.example.employeesearch.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxServiceTest {

    private TaxService taxService;

    @BeforeEach
    void setUp() {
        taxService = new TaxService();
    }

    @ParameterizedTest(name = "Salary: {0} => Expected Tax: {1}")
    @CsvSource({
            "0.0, 0.0",
            "250000.0, 0.0",
            "300000.0, 0.0",
            "300001.0, 15000.05",
            "550000.0, 27500.0",
            "600000.0, 30000.0",
            "650000.0, 65000.0",
            "750000.0, 75000.0",
            "950000.0, 95000.0",
            "1000000.0, 100000.0",
            "1200000.0, 240000.0"
    })
    @DisplayName("Test tax calculation across all tax slab boundaries")
    void testTaxCalculationBoundaries(double salary, double expectedTax) {
        double actualTax = taxService.calculateAnnualTax(salary);
        assertEquals(expectedTax, actualTax, 0.01);
    }

    @Test
    @DisplayName("Test annual and monthly net salary calculation")
    void testNetSalaryCalculation() {
        double salary = 750000.0; // 10% slab -> 75000 tax -> 675000 net
        assertEquals(675000.0, taxService.calculateAnnualNetSalary(salary), 0.01);
        assertEquals(56250.0, taxService.calculateMonthlyNetSalary(salary), 0.01);
    }
}
