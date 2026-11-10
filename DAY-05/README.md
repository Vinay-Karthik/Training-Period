# Employee Management and Payroll Processing Application (Week 1 Assessment)

## Architecture Overview

This application is designed using a multi-layered Java 21 architecture:

- **`model`**: Domain models featuring modern Java 21 `record` types (`EmployeeSummary`), sealed classes (`Employee`
  base permitting `PermanentEmployee`, `ContractEmployee`, `Manager`), and enumerations.
- **`dto`**: Data Transfer Objects (`PayrollResult`, `DepartmentPayrollSummary`, `SalaryDistribution`).
- **`repository`**: In-memory data persistence utilizing Java Collections (`ArrayList`, `HashSet`, `HashMap`,
  `LinkedHashMap`, `TreeSet`, `ConcurrentHashMap`).
- **`service`**: Core business services implementing employee management, searching, sorting, Streams-based payroll
  aggregation, and tax calculation.
- **`exception`**: Custom runtime exception hierarchy (`EmployeeNotFoundException`, `DuplicateEmployeeException`,
  `InvalidEmployeeDataException`, `PayrollProcessingException`).
- **`util`**: Validation engine (`InputValidator`).
- **`Main`**: Interactive CLI controller.

---

## Technical Highlights & Features

1. **Java 21 Core Features**:
    - Sealed class hierarchy (`Employee` sealed with `permits`).
    - Lightweight `record` representations.
    - Switch expressions and pattern matching with `instanceof`.
2. **Collections & Generics**:
    - Duplicate prevention with `HashSet` on ID and Email.
    - Generics with bounded type parameters and PECS (`? extends Employee`, `? super Employee`).
    - Thread-safe operations using `ConcurrentHashMap`.
3. **Streams & Functional Programming**:
    - Stream transformations (`map`, `filter`, `flatMap`, `reduce`).
    - Grouping and partitioning collectors (`Collectors.groupingBy`, `Collectors.summarizingDouble`).
    - Parallel streams performance benchmarking.

---

## Build & Execution Instructions

### Prerequisites

- JDK 21 installed.
- Apache Maven 3.8+ installed.

### Build the Project

```bash
mvn clean compile
```

### Run Automated Unit Tests (JUnit 5)

```bash
mvn test
```

### Launch the Application

```bash
mvn compile exec:java -Dexec.mainClass="com.company.hr.Main"
```
