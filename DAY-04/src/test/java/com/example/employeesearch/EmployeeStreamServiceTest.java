package com.example.employeesearch;

import com.example.employeesearch.dto.DepartmentPayrollSummary;
import com.example.employeesearch.model.Department;
import com.example.employeesearch.model.Employee;
import com.example.employeesearch.service.DepartmentReportService;
import com.example.employeesearch.service.EmployeeStreamService;
import com.example.employeesearch.service.SalaryAnalyticsService;
import com.example.employeesearch.service.TaxService;
import com.example.employeesearch.util.PayrollDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStreamServiceTest {

    private List<Employee> sampleEmployees;
    private EmployeeStreamService streamService;
    private DepartmentReportService departmentReportService;
    private SalaryAnalyticsService analyticsService;
    private TaxService taxService;

    @BeforeEach
    void setUp() {
        sampleEmployees = PayrollDataProvider.getSampleEmployees();
        streamService = new EmployeeStreamService();
        departmentReportService = new DepartmentReportService();
        analyticsService = new SalaryAnalyticsService();
        taxService = new TaxService();
    }

    @Test
    @DisplayName("Test total payroll calculation using stream reduce")
    void testTotalPayrollReduce() {
        double total = streamService.calculateTotalPayrollExpenditure(sampleEmployees);
        assertEquals(7950000.0, total, 0.01);
    }

    @Test
    @DisplayName("Test Department-wise aggregation using groupingBy")
    void testDepartmentAggregation() {
        Map<Department, Long> counts = departmentReportService.getEmployeeCountByDepartment(sampleEmployees);
        assertEquals(4L, counts.get(Department.ENGINEERING));
        assertEquals(2L, counts.get(Department.HUMAN_RESOURCES));
        assertEquals(2L, counts.get(Department.FINANCE));
        assertEquals(2L, counts.get(Department.SALES));

        Map<Department, Double> totalSalaries = departmentReportService.getTotalSalaryByDepartment(sampleEmployees);
        assertEquals(3250000.0, totalSalaries.get(Department.ENGINEERING), 0.01);
        assertEquals(1650000.0, totalSalaries.get(Department.HUMAN_RESOURCES), 0.01);
        assertEquals(1400000.0, totalSalaries.get(Department.FINANCE), 0.01);
        assertEquals(1450000.0, totalSalaries.get(Department.SALES), 0.01);
    }

    @Test
    @DisplayName("Test FlatMap department employee lists")
    void testFlatMapDepartmentLists() {
        List<List<Employee>> deptLists = PayrollDataProvider.getDepartmentEmployeeLists();
        List<Employee> flattened = streamService.flattenDepartmentLists(deptLists);
        assertEquals(10, flattened.size());
    }

    @Test
    @DisplayName("Test Sequential vs Parallel Stream consistency")
    void testParallelConsistency() {
        List<Employee> largeList = PayrollDataProvider.generateLargeDataset(5000);
        double seqSum = largeList.stream().mapToDouble(Employee::salary).sum();
        double parSum = largeList.parallelStream().mapToDouble(Employee::salary).sum();
        assertEquals(seqSum, parSum, 0.001);
    }
}
