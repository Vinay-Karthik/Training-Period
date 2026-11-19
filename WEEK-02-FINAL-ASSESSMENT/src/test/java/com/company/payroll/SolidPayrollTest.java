package com.company.payroll;

import com.company.payroll.model.*;
import com.company.payroll.notification.ConsoleNotificationService;
import com.company.payroll.repository.InMemoryEmployeeRepository;
import com.company.payroll.repository.InMemoryPayrollRepository;
import com.company.payroll.service.DeductionService;
import com.company.payroll.service.PayrollService;
import com.company.payroll.solid_bad.BadContractEmployee;
import com.company.payroll.solid_bad.BadEmployee;
import com.company.payroll.solid_bad.BadPayrollService;
import com.company.payroll.strategy.salary.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simplified SOLID Payroll Framework Tests")
class SolidPayrollTest {

    private InMemoryEmployeeRepository empRepo;
    private InMemoryPayrollRepository payrollRepo;
    private PayrollService payrollService;

    @BeforeEach
    void setUp() {
        empRepo = new InMemoryEmployeeRepository();
        payrollRepo = new InMemoryPayrollRepository();
        DeductionService deductionService = new DeductionService();
        ConsoleNotificationService notificationService = new ConsoleNotificationService();

        payrollService = new PayrollService(empRepo, payrollRepo, notificationService, deductionService);
        payrollService.registerSalaryStrategy(EmployeeType.PERMANENT, new PermanentSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.CONTRACT, new ContractSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.SALES, new SalesSalaryStrategy());
        payrollService.registerSalaryStrategy(EmployeeType.EXECUTIVE, new ExecutiveSalaryStrategy());
    }

    @Test
    @DisplayName("LSP Test: BadContractEmployee throws exception in bad design")
    void testBadDesignLspViolation() {
        BadPayrollService badService = new BadPayrollService();
        BadEmployee badContract = new BadContractEmployee("BAD-01", "Bob", 50000, "bob@test.com");

        assertThrows(UnsupportedOperationException.class, () -> badService.processPayroll(badContract));
    }

    @Test
    @DisplayName("LSP Test: ContractEmployee processes cleanly without exceptions in refactored design")
    void testRefactoredDesignLspCompliance() {
        ContractEmployee contractEmp = new ContractEmployee("C-01", "Bob", "IT", "NEW", "bob@test.com", 60000);
        PayrollResult result = payrollService.processPayroll(contractEmp);

        assertNotNull(result);
        assertEquals(60000, result.getGrossSalary());
        assertEquals(0, result.getDeductions());
        assertTrue(result.getNetSalary() > 0);
    }

    @Test
    @DisplayName("OCP Test: New Freelancer strategy can be registered at runtime without altering PayrollService")
    void testOcpExtensibility() {
        payrollService.registerSalaryStrategy(EmployeeType.FREELANCER, emp -> 40000.0);
        Employee freelancer = new Employee("F-01", "Freelancer", EmployeeType.FREELANCER, "Design", "NEW", "f@test.com") {};

        PayrollResult result = payrollService.processPayroll(freelancer);
        assertEquals(40000.0, result.getGrossSalary());
    }

    @Test
    @DisplayName("SRP & Calculation Test: Permanent employee calculates Basic + HRA + Allowances correctly")
    void testPermanentEmployeeCalculation() {
        PermanentEmployee perm = new PermanentEmployee("P-01", "Alice", "Eng", "OLD", "alice@test.com", 50000, 20000, 10000);
        PayrollResult result = payrollService.processPayroll(perm);

        assertEquals(80000.0, result.getGrossSalary());
        assertTrue(result.getDeductions() > 0);
        assertTrue(result.getTax() > 0);
        assertEquals(result.getGrossSalary() - result.getDeductions() - result.getTax(), result.getNetSalary());
    }

    @Test
    @DisplayName("DIP Test: Results are saved to repository abstraction")
    void testDependencyInversion() {
        Employee emp = new ContractEmployee("C-02", "Charlie", "Operations", "NEW", "c@test.com", 50000);
        payrollService.processPayroll(emp);

        assertEquals(1, payrollRepo.findAll().size());
        assertEquals("C-02", payrollRepo.findAll().get(0).getEmployeeId());
    }
}
