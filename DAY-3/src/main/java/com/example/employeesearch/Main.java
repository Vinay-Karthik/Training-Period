package com.example.employeesearch;

import com.example.employeesearch.model.*;
import com.example.employeesearch.repository.*;
import com.example.employeesearch.service.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final EmployeeRepository repository = new EmployeeRepository();
    private static final EmployeeSearchService searchService = new EmployeeSearchService();
    private static final EmployeeSortingService sortingService = new EmployeeSortingService();
    private static final EmployeeGroupingService groupingService = new EmployeeGroupingService();
    private static final EmployeeAnalyticsService analyticsService = new EmployeeAnalyticsService();
    private static final EmployeeIteratorService iteratorService = new EmployeeIteratorService();

    public static void main(String[] args) {
        seedDataset();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 15.");
                continue;
            }

            switch (choice) {
                case 1 -> registerEmployeeUI(scanner);
                case 2 -> viewAllEmployees();
                case 3 -> searchByIdUI(scanner);
                case 4 -> searchByNameUI(scanner);
                case 5 -> filterByDepartmentUI(scanner);
                case 6 -> filterByEmploymentTypeUI(scanner);
                case 7 -> filterBySalaryUI(scanner);
                case 8 -> sortingSubMenu(scanner);
                case 9 -> groupingSubMenu(scanner);
                case 10 -> detectDuplicates();
                case 11 -> analyticsService.printSalaryReport(repository.findAll());
                case 12 -> deleteEmployeeUI(scanner);
                case 13 -> runConcurrentTest();
                case 14 -> runIteratorDemos();
                case 15 -> {
                    System.out.println("Exiting Employee Search & Filter Engine. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select between 1 and 15.");
            }
            System.out.println();
        }
    }

    private static void seedDataset() {
        repository.addEmployee(new Employee("EMP001", "Rahul", "rahul@company.com", 75000, Department.ENGINEERING, Designation.SENIOR_SOFTWARE_ENGINEER, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP002", "Priya", "priya@company.com", 95000, Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP003", "Arjun", "arjun@company.com", 120000, Department.ENGINEERING, Designation.ENGINEERING_MANAGER, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP004", "Sneha", "sneha@company.com", 55000, Department.FINANCE, Designation.ACCOUNTANT, EmploymentType.CONTRACT));
        repository.addEmployee(new Employee("EMP005", "Kiran", "kiran@company.com", 65000, Department.SALES, Designation.SALES_EXECUTIVE, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP006", "Ananya", "ananya@company.com", 105000, Department.ENGINEERING, Designation.TECH_LEAD, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP007", "Vikram", "vikram@company.com", 70000, Department.HUMAN_RESOURCES, Designation.HR_MANAGER, EmploymentType.CONTRACT));
        repository.addEmployee(new Employee("EMP008", "Meera", "meera@company.com", 85000, Department.FINANCE, Designation.ACCOUNTANT, EmploymentType.PERMANENT));
        repository.addEmployee(new Employee("EMP009", "Rohit", "rohit@company.com", 25000, Department.ENGINEERING, Designation.INTERN, EmploymentType.INTERN));
        repository.addEmployee(new Employee("EMP010", "Divya", "divya@company.com", 80000, Department.SALES, Designation.SALES_EXECUTIVE, EmploymentType.PERMANENT));
    }

    private static void printMenu() {
        System.out.println("=================================================");
        System.out.println("          EMPLOYEE SEARCH & FILTER ENGINE        ");
        System.out.println("=================================================");
        System.out.println("1.  Register Employee");
        System.out.println("2.  View All Employees");
        System.out.println("3.  Search Employee By ID");
        System.out.println("4.  Search Employee By Name");
        System.out.println("5.  Filter Employees By Department");
        System.out.println("6.  Filter Employees By Employment Type");
        System.out.println("7.  Filter Employees By Minimum Salary");
        System.out.println("8.  Sort Employees");
        System.out.println("9.  Group Employees");
        System.out.println("10. Detect Duplicate Employees");
        System.out.println("11. Generate Salary Analytics");
        System.out.println("12. Delete Employee");
        System.out.println("13. Concurrent Registration Test");
        System.out.println("14. Iterator Demonstration");
        System.out.println("15. Exit");
        System.out.println("=================================================");
    }

    private static void registerEmployeeUI(Scanner scanner) {
        try {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine().trim();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Departments: " + Arrays.toString(Department.values()));
            System.out.print("Enter Department: ");
            Department dept = Department.valueOf(scanner.nextLine().trim().toUpperCase());

            System.out.println("Designations: " + Arrays.toString(Designation.values()));
            System.out.print("Enter Designation: ");
            Designation desg = Designation.valueOf(scanner.nextLine().trim().toUpperCase());

            System.out.println("Employment Types: " + Arrays.toString(EmploymentType.values()));
            System.out.print("Enter Employment Type: ");
            EmploymentType type = EmploymentType.valueOf(scanner.nextLine().trim().toUpperCase());

            Employee emp = new Employee(id, name, email, salary, dept, desg, type);
            if (repository.addEmployee(emp)) {
                System.out.println("Employee registered successfully!");
            } else {
                System.out.println("Error: Employee ID already exists.");
            }
        } catch (Exception e) {
            System.out.println("Failed to register employee: " + e.getMessage());
        }
    }

    private static void viewAllEmployees() {
        List<Employee> list = repository.findAll();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void searchByIdUI(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine().trim();
        Optional<Employee> emp = repository.findById(id);
        emp.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Employee with ID " + id + " not found.")
        );
    }

    private static void searchByNameUI(Scanner scanner) {
        System.out.print("Enter Name (or substring): ");
        String name = scanner.nextLine().trim();
        List<Employee> matches = searchService.searchByName(repository.findAll(), name);
        if (matches.isEmpty()) {
            System.out.println("No matching employees found.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    private static void filterByDepartmentUI(Scanner scanner) {
        System.out.print("Enter Department: ");
        try {
            Department dept = Department.valueOf(scanner.nextLine().trim().toUpperCase());
            List<Employee> matches = searchService.searchByDepartment(repository.findAll(), dept);
            matches.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Department.");
        }
    }

    private static void filterByEmploymentTypeUI(Scanner scanner) {
        System.out.print("Enter Employment Type: ");
        try {
            EmploymentType type = EmploymentType.valueOf(scanner.nextLine().trim().toUpperCase());
            List<Employee> matches = searchService.searchByEmploymentType(repository.findAll(), type);
            matches.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Employment Type.");
        }
    }

    private static void filterBySalaryUI(Scanner scanner) {
        System.out.print("Enter Minimum Salary: ");
        try {
            double minSal = Double.parseDouble(scanner.nextLine().trim());
            List<Employee> matches = searchService.searchByMinimumSalary(repository.findAll(), minSal);
            matches.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary number.");
        }
    }

    private static void sortingSubMenu(Scanner scanner) {
        System.out.println("1. Sort by ID (Natural)");
        System.out.println("2. Sort by Name");
        System.out.println("3. Sort by Salary Ascending");
        System.out.println("4. Sort by Salary Descending");
        System.out.println("5. Department Ascending, Salary Descending");
        System.out.println("6. Salary Descending, ID Ascending");
        System.out.println("7. Employment Type Ascending, Name Ascending");
        System.out.print("Choice: ");
        String ch = scanner.nextLine().trim();
        List<Employee> all = repository.findAll();
        List<Employee> sorted = switch (ch) {
            case "1" -> sortingService.sortById(all);
            case "2" -> sortingService.sortByName(all);
            case "3" -> sortingService.sortBySalaryAscending(all);
            case "4" -> sortingService.sortBySalaryDescending(all);
            case "5" -> sortingService.sortByDepartmentAscAndSalaryDesc(all);
            case "6" -> sortingService.sortBySalaryDescAndIdAsc(all);
            case "7" -> sortingService.sortByEmploymentTypeAscAndNameAsc(all);
            default -> {
                System.out.println("Invalid choice.");
                yield List.of();
            }
        };
        sorted.forEach(System.out::println);
    }

    private static void groupingSubMenu(Scanner scanner) {
        System.out.println("1. Group by Department");
        System.out.println("2. Group by Employment Type");
        System.out.println("3. Group Nested (Department -> Employment Type)");
        System.out.println("4. Highest Paid per Department");
        System.out.print("Choice: ");
        String ch = scanner.nextLine().trim();
        List<Employee> all = repository.findAll();
        switch (ch) {
            case "1" -> groupingService.groupByDepartment(all).forEach((dept, list) -> {
                System.out.println(dept + ":");
                list.forEach(e -> System.out.println("  " + e.name()));
            });
            case "2" -> groupingService.groupByEmploymentType(all).forEach((type, list) -> {
                System.out.println(type + ":");
                list.forEach(e -> System.out.println("  " + e.name()));
            });
            case "3" -> groupingService.groupNested(all).forEach((dept, map) -> {
                System.out.println(dept + ":");
                map.forEach((type, list) -> {
                    System.out.println("  " + type + ":");
                    list.forEach(e -> System.out.println("    " + e.name()));
                });
            });
            case "4" -> groupingService.findHighestPaidEmployeeByDepartment(all).forEach((dept, emp) ->
                    System.out.printf("%-18s -> %s (%.2f)%n", dept, emp.name(), emp.salary())
            );
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void detectDuplicates() {
        List<Employee> all = repository.findAll();
        Set<String> dupIds = groupingService.findDuplicateEmployeeIds(all);
        Set<String> dupEmails = groupingService.findDuplicateEmails(all);
        System.out.println("Duplicate IDs: " + dupIds);
        System.out.println("Duplicate Emails: " + dupEmails);
    }

    private static void deleteEmployeeUI(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        String id = scanner.nextLine().trim();
        if (repository.deleteEmployee(id)) {
            System.out.println("Employee " + id + " deleted successfully.");
        } else {
            System.out.println("Employee ID not found.");
        }
    }

    private static void runConcurrentTest() {
        System.out.println("Starting Concurrent Registration Test with 10 Threads...");
        ConcurrentEmployeeRepository concRepo = new ConcurrentEmployeeRepository();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successCount = new AtomicInteger(0);

        Employee target = new Employee("EMP999", "TestUser", "test@company.com", 60000, Department.ENGINEERING, Designation.SOFTWARE_ENGINEER, EmploymentType.PERMANENT);

        for (int i = 1; i <= 10; i++) {
            final int threadNum = i;
            executor.submit(() -> {
                boolean success = concRepo.addEmployee(target);
                if (success) {
                    successCount.incrementAndGet();
                    System.out.println("Thread-" + threadNum + ": Registration successful");
                } else {
                    System.out.println("Thread-" + threadNum + ": Duplicate employee");
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println("Successful registrations: " + successCount.get());
        System.out.println("Total employees: " + concRepo.count());
    }

    private static void runIteratorDemos() {
        List<Employee> all = new ArrayList<>(repository.findAll());
        iteratorService.demonstrateIterator(all);
        iteratorService.demonstrateListIterator(all);
        iteratorService.demonstrateFailFast(all);
        iteratorService.demonstrateSnapshotIteration(all);
    }
}
