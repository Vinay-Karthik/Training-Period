package com.company.hr;

import com.company.hr.dto.DepartmentPayrollSummary;
import com.company.hr.dto.PayrollResult;
import com.company.hr.dto.SalaryDistribution;
import com.company.hr.model.Department;
import com.company.hr.model.Designation;
import com.company.hr.model.Employee;
import com.company.hr.model.EmploymentType;
import com.company.hr.repository.EmployeeRepository;
import com.company.hr.service.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final EmployeeRepository repository = new EmployeeRepository();
    private static final EmployeeService employeeService = new EmployeeService(repository);
    private static final TaxService taxService = new TaxService();
    private static final PayrollService payrollService = new PayrollService(taxService);
    private static final EmployeeSearchService searchService = new EmployeeSearchService();
    private static final EmployeeReportService reportService = new EmployeeReportService(taxService);

    public static void main(String[] args) {
        seedInitialDataset();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> registerEmployeeUI(scanner);
                    case 2 -> viewAllEmployeesUI();
                    case 3 -> searchEmployeeByIdUI(scanner);
                    case 4 -> searchEmployeeByNameUI(scanner);
                    case 5 -> filterEmployeesByDepartmentUI(scanner);
                    case 6 -> updateEmployeeUI(scanner);
                    case 7 -> deleteEmployeeUI(scanner);
                    case 8 -> sortEmployeesUI(scanner);
                    case 9 -> generatePayrollUI();
                    case 10 -> departmentPayrollSummaryUI();
                    case 11 -> salaryAnalyticsUI();
                    case 12 -> detectDuplicateEmployeesUI();
                    case 13 -> {
                        System.out.println(" Exiting Employee Management System.Goodbye !");
                        running = false;
                    }
                    default -> System.out.println("Invalid option! Please enter a number between 1 and 13.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a valid menu number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(" Press Enter to return to menu...");
            scanner.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println(" == == == == == == == == == == == == == == == == == == == == ");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM       ");
        System.out.println("========================================");
        System.out.println("1. Register Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee By ID");
        System.out.println("4. Search Employee By Name");
        System.out.println("5. Filter 2" +
                "Employees By Department");
        System.out.println("6. Update Employee");
        System.out.println("7. Delete Employee");
        System.out.println("8. Sort Employees");
        System.out.println("9. Generate Payroll");
        System.out.println("10. Department Payroll Summary");
        System.out.println("11. Salary Analytics");
        System.out.println("12. Detect Duplicate Employees");
        System.out.println("13. Exit");
        System.out.println("----------------------------------------");
    }

    private static void seedInitialDataset() {
        try {
            employeeService.registerEmployee("EMP001", "Rahul", "rahul@company.com", Department.ENGINEERING, Designation.SENIOR_ENGINEER, EmploymentType.PERMANENT, 750000.0);
            employeeService.registerEmployee("EMP002", "Priya", "priya@company.com", Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.PERMANENT, 950000.0);
            employeeService.registerEmployee("EMP003", "Arjun", "arjun@company.com", Department.ENGINEERING, Designation.LEAD_ENGINEER, EmploymentType.PERMANENT, 1200000.0);
            employeeService.registerEmployee("EMP004", "Sneha", "sneha@company.com", Department.FINANCE, Designation.FINANCIAL_ANALYST, EmploymentType.CONTRACT, 550000.0);
            employeeService.registerEmployee("EMP005", "Kiran", "kiran@company.com", Department.SALES, Designation.SALES_EXECUTIVE, EmploymentType.PERMANENT, 650000.0);
            employeeService.registerEmployee("EMP006", "Ananya", "ananya@company.com", Department.ENGINEERING, Designation.SENIOR_ENGINEER, EmploymentType.PERMANENT, 1050000.0);
            employeeService.registerEmployee("EMP007", "Vikram", "vikram@company.com", Department.HUMAN_RESOURCES, Designation.HR_ASSOCIATE, EmploymentType.CONTRACT, 700000.0);
            employeeService.registerEmployee("EMP008", "Meera", "meera@company.com", Department.FINANCE, Designation.FINANCE_MANAGER, EmploymentType.PERMANENT, 850000.0);
            employeeService.registerEmployee("EMP009", "Rohit", "rohit@company.com", Department.ENGINEERING, Designation.INTERN, EmploymentType.INTERN, 250000.0);
            employeeService.registerEmployee("EMP010", "Divya", "divya@company.com", Department.SALES, Designation.SALES_MANAGER, EmploymentType.PERMANENT, 800000.0);
        } catch (Exception e) {
            System.err.println("Seeding error: " + e.getMessage());
        }
    }

    private static void registerEmployeeUI(Scanner scanner) {
        System.out.println(" -- - Register New Employee-- - ");
        String id = scanner.nextLine().trim();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Select Department:");
        for (Department d : Department.values()) {
            System.out.println("  " + d.ordinal() + ". " + d.name());
        }
        System.out.print("Choice index: ");
        int deptIdx = Integer.parseInt(scanner.nextLine().trim());
        Department dept = Department.values()[deptIdx];

        System.out.println("Select Designation:");
        for (Designation d : Designation.values()) {
            System.out.println("  " + d.ordinal() + ". " + d.name());
        }
        System.out.print("Choice index: ");
        int desgIdx = Integer.parseInt(scanner.nextLine().trim());
        Designation desg = Designation.values()[desgIdx];

        System.out.println("Select Employment Type:");
        for (EmploymentType t : EmploymentType.values()) {
            System.out.println("  " + t.ordinal() + ". " + t.name());
        }
        System.out.print("Choice index: ");
        int typeIdx = Integer.parseInt(scanner.nextLine().trim());
        EmploymentType type = EmploymentType.values()[typeIdx];

        System.out.print("Annual Base Salary (₹): ");
        double salary = Double.parseDouble(scanner.nextLine().trim());

        employeeService.registerEmployee(id, name, email, dept, desg, type, salary);
        System.out.println("Successfully registered employee " + id + "!");
    }

    private static void viewAllEmployeesUI() {
        System.out.println(" -- - All Registered Employees-- - ");
        List<Employee> list = employeeService.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.printf("%-8s %-12s %-22s %-18s %-12s %-14s%n", "ID", "Name", "Email", "Department", "Type", "Salary");
        System.out.println("-".repeat(90));
        for (Employee e : list) {
            System.out.printf("%-8s %-12s %-22s %-18s %-12s ₹%-14.2f%n",
                    e.getId(), e.getName(), e.getEmail(), e.getDepartment(), e.getEmploymentType(), e.calculateAnnualGrossSalary());
        }
    }

    private static void searchEmployeeByIdUI(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine().trim();
        Employee emp = employeeService.getEmployeeById(id);
        System.out.println("Found Record: " + emp);
        System.out.println("Policy Note: " + employeeService.handleEmploymentTypePolicy(emp));
    }

    private static void searchEmployeeByNameUI(Scanner scanner) {
        System.out.print("Enter search keyword for Name: ");
        String name = scanner.nextLine().trim();
        List<Employee> results = searchService.searchByName(employeeService.getAllEmployees(), name);
        System.out.println("Found " + results.size() + " matches:");
        results.forEach(e -> System.out.println("  • " + e));
    }

    private static void filterEmployeesByDepartmentUI(Scanner scanner) {
        System.out.println("Select Department:");
        for (Department d : Department.values()) {
            System.out.println("  " + d.ordinal() + ". " + d.name());
        }
        System.out.print("Index: ");
        int idx = Integer.parseInt(scanner.nextLine().trim());
        Department dept = Department.values()[idx];

        List<Employee> filtered = searchService.filterByDepartment(employeeService.getAllEmployees(), dept);
        System.out.println("Employees in " + dept + " (" + filtered.size() + "):");
        filtered.forEach(e -> System.out.println("  • " + e));
    }

    private static void updateEmployeeUI(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        String id = scanner.nextLine().trim();
        Employee existing = employeeService.getEmployeeById(id);

        System.out.print("New Name [" + existing.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();

        System.out.print("New Email [" + existing.getEmail() + "]: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) email = existing.getEmail();

        System.out.print("New Salary [" + existing.getBaseSalary() + "]: ");
        String salStr = scanner.nextLine().trim();
        double salary = salStr.isEmpty() ? existing.getBaseSalary() : Double.parseDouble(salStr);

        employeeService.updateEmployee(id, name, email, existing.getDepartment(), existing.getDesignation(), salary);
        System.out.println("Updated successfully.");
    }

    private static void deleteEmployeeUI(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        String id = scanner.nextLine().trim();
        employeeService.deleteEmployee(id);
        System.out.println("Employee " + id + " deleted successfully.");
    }

    private static void sortEmployeesUI(Scanner scanner) {
        System.out.println("Sort options: \n1. By Name  \n28" +
                ". By Salary Descending");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        List<Employee> sorted = (choice == 1) ?
                searchService.sortByName(employeeService.getAllEmployees()) :
                searchService.sortBySalaryDescending(employeeService.getAllEmployees());

        System.out.println("Sorted Results:");
        sorted.forEach(e -> System.out.println("  • " + e));
    }

    private static void generatePayrollUI() {
        System.out.println("-- - GENERATED PAYROLL RESULTS-- - ");
        List<PayrollResult> results = payrollService.processPayrollForAll(employeeService.getAllEmployees());
        System.out.printf("%-8s %-12s %-18s %-15s %-15s %-15s%n", "ID", "Name", "Department", "Annual Gross", "Annual Tax", "Annual Net");
        System.out.println("-".repeat(85));
        for (PayrollResult r : results) {
            System.out.printf("%-8s %-12s %-18s ₹%-14.2f ₹%-14.2f ₹%-14.2f%n",
                    r.employeeId(), r.employeeName(), r.department(), r.annualGrossSalary(), r.annualTax(), r.annualNetSalary());
        }
    }

    private static void departmentPayrollSummaryUI() {
        System.out.println(" -- - DEPARTMENT PAYROLL SUMMARY-- - ");
        List<DepartmentPayrollSummary> summaries = reportService.generateDepartmentSummaries(employeeService.getAllEmployees());
        System.out.printf("%-20s %-10s %-20s %-20s %-15s%n", "Department", "Count", "Total Salary", "Average Salary", "Total Tax");
        System.out.println("-".repeat(90));
        for (DepartmentPayrollSummary s : summaries) {
            System.out.printf("%-20s %-10d ₹%-19.2f ₹%-19.2f ₹%-14.2f%n",
                    s.department(), s.employeeCount(), s.totalAnnualSalary(), s.averageAnnualSalary(), s.totalAnnualTax());
        }
    }

    private static void salaryAnalyticsUI() {
        List<Employee> list = employeeService.getAllEmployees();
        double total = payrollService.calculateTotalPayrollExpenditure(list);
        double avg = payrollService.calculateAverageSalary(list);
        SalaryDistribution dist = reportService.calculateSalaryDistribution(list);

        System.out.println(" -- - SALARY ANALYTICS & OVERVIEW-- - ");
        System.out.printf("Total Organization Expenditure: ₹%.2f%n", total);
        System.out.printf("Average Employee Salary:        ₹%.2f%n", avg);
        System.out.println("Distribution breakdown:");
        System.out.println("  • Below ₹5 Lakh:    " + dist.countBelow5Lakh() + " employees");
        System.out.println("  • ₹5 Lakh – ₹10 Lakh: " + dist.count5To10Lakh() + " employees");
        System.out.println("  • Above ₹10 Lakh:   " + dist.countAbove10Lakh() + " employees");

        reportService.compareSequentialAndParallelExecution(list);
    }

    private static void detectDuplicateEmployeesUI() {
        System.out.println(" -- - DUPLICATE RECORD DETECTION-- - ");
        Set<String> dupes = repository.findDuplicateIdsOrEmails(employeeService.getAllEmployees());
        if (dupes.isEmpty()) {
            System.out.println("No duplicate IDs or Email records detected in system.");
        } else {
            System.out.println("Duplicate records found:");
            dupes.forEach(d -> System.out.println("  • " + d));
        }
    }
}
