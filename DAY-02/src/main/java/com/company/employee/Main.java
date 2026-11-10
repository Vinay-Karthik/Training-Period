package com.company.employee;

import com.company.employee.model.ContractEmployee;
import com.company.employee.model.Department;
import com.company.employee.model.Designation;
import com.company.employee.model.Employee;
import com.company.employee.model.Manager;
import com.company.employee.model.PermanentEmployee;
import com.company.employee.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> EmployeeService.displayAllEmployees();
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> searchByNameAndDepartment();
                case 6 -> {
                    EmployeeService.sortById();
                    System.out.println("Employees sorted by ID.");
                }
                case 7 -> {
                    EmployeeService.sortBySalary();
                    System.out.println("Employees sorted by salary.");
                }
                case 8 -> calculateSalary();
                case 9 -> System.out.println("Total Employees: " + EmployeeService.getEmployeeCount());
                case 10 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please enter 1-10.");
            }
            System.out.println();
        }
        SCANNER.close();
    }

    private static void printMenu() {
        System.out.println("========== EMPLOYEE MANAGEMENT ==========");
        System.out.println();
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Search Employee by Name");
        System.out.println("5. Search by Name and Department");
        System.out.println("6. Sort Employees by ID");
        System.out.println("7. Sort Employees by Salary");
        System.out.println("8. Calculate Salary");
        System.out.println("9. Employee Count");
        System.out.println("10. Exit");
        System.out.println();
    }

    private static void addEmployee() {
        try {
            System.out.println("Select Employee Type:");
            System.out.println("1. Permanent");
            System.out.println("2. Contract");
            System.out.println("3. Manager");
            int typeChoice = readInt("Enter type (1-3): ");

            String name = readLine("Enter name: ");
            String email = readLine("Enter email: ");
            double salary = readDouble("Enter annual salary: ");
            String deptName = readLine("Enter department: ");
            String desigTitle = readLine("Enter designation: ");

            Department department = new Department(deptName);
            Designation designation = new Designation(desigTitle);
            long id = EmployeeService.nextEmployeeId();

            Employee employee = switch (typeChoice) {
                case 1 -> {
                    double bonus = readDouble("Enter annual bonus: ");
                    yield new PermanentEmployee(id, name, email, salary, department, designation, bonus);
                }
                case 2 -> {
                    int months = readInt("Enter contract duration (months): ");
                    yield new ContractEmployee(id, name, email, salary, department, designation, months);
                }
                case 3 -> {
                    int teamSize = readInt("Enter team size: ");
                    yield new Manager(id, name, email, salary, department, designation, teamSize);
                }
                default -> throw new IllegalArgumentException("Invalid employee type");
            };

            EmployeeService.addEmployee(employee);
            System.out.println("Employee " + employee.getEmployeeId() + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchById() {
        long id = readLong("Enter employee ID number: ");
        Employee employee = EmployeeService.searchEmployee(id);
        if (employee == null) {
            System.out.println("No employee found with ID: " + id);
        } else {
            System.out.println();
            employee.displayDetails();
        }
    }

    private static void searchByName() {
        String name = readLine("Enter name to search: ");
        List<Employee> matches = EmployeeService.searchEmployee(name);
        printSearchResults(matches);
    }

    private static void searchByNameAndDepartment() {
        String name = readLine("Enter name to search: ");
        String dept = readLine("Enter department: ");
        List<Employee> matches = EmployeeService.searchEmployee(name, dept);
        printSearchResults(matches);
    }

    private static void printSearchResults(List<Employee> matches) {
        if (matches.isEmpty()) {
            System.out.println("No matching employees found.");
            return;
        }
        System.out.println();
        for (Employee employee : matches) {
            employee.displayDetails();
            System.out.println();
        }
    }

    private static void calculateSalary() {
        long id = readLong("Enter employee ID number: ");
        Employee employee = EmployeeService.searchEmployee(id);
        if (employee == null) {
            System.out.println("No employee found with ID: " + id);
            return;
        }
        double monthly = EmployeeService.calculateMonthlySalary(employee);
        System.out.println("Employee: " + employee.getName() + " (" + employee.getEmployeeId() + ")");
        System.out.println("Monthly Salary: " + monthly);
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (SCANNER.hasNextInt()) {
                int value = SCANNER.nextInt();
                SCANNER.nextLine(); // consume newline
                return value;
            }
            System.out.println("Invalid input. Please enter a whole number.");
            SCANNER.nextLine();
        }
    }

    private static long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (SCANNER.hasNextLong()) {
                long value = SCANNER.nextLong();
                SCANNER.nextLine(); // consume newline
                return value;
            }
            System.out.println("Invalid input. Please enter a number.");
            SCANNER.nextLine();
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (SCANNER.hasNextDouble()) {
                double value = SCANNER.nextDouble();
                SCANNER.nextLine(); // consume newline
                return value;
            }
            System.out.println("Invalid input. Please enter a number.");
            SCANNER.nextLine();
        }
    }
}
