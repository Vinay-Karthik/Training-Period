# Milestone 1: Simplified SOLID Payroll Framework

**Course:** Week 2 Final Assessment – Design Patterns & SOLID  
**Platform:** Java 21 / Maven / JUnit 5  
**Deliverable:** Bad Payroll vs. Refactored SOLID Payroll Framework  

---

## 1. Overview & Concept Breakdown

This project refactors a tightly coupled, monolithic payroll system into a clean, modular architecture that clearly illustrates all 5 SOLID principles without unnecessary layers of complexity.

```
com.company.payroll
│
├── solid_bad                                 # 1. BAD PAYROLL (Violations)
│   ├── BadEmployee.java                      # Bloated class forcing all fields
│   ├── BadContractEmployee.java              # LSP Violation (throws UnsupportedOperationException)
│   └── BadPayrollService.java                # God Class (SRP, OCP, DIP, ISP violations)
│
├── model                                     # 2. CLEAN DOMAIN ENTITIES
│   ├── Employee.java                         # Clean base class with shared attributes
│   ├── EmployeeType.java                     # PERMANENT, CONTRACT, SALES, EXECUTIVE, FREELANCER
│   ├── PermanentEmployee.java                # Basic + HRA + Allowances
│   ├── ContractEmployee.java                 # Fixed contract amount (Safe LSP)
│   ├── SalesEmployee.java                    # Base + Commission
│   ├── ExecutiveEmployee.java                # Fixed + Bonus + Benefits
│   ├── PayrollResult.java                    # Clean payroll record
│   └── interfaces                            # Focused ISP contracts
│       ├── HasHra.java                       # getBasicSalary(), getHra(), getAllowances()
│       ├── HasCommission.java                # getSalesAmount(), getCommissionRate()
│       └── HasExecutiveBenefits.java         # getBonus(), getBenefits()
│
├── strategy                                  # 3. OCP & STRATEGY PATTERNS
│   ├── salary                                # Gross pay strategies
│   │   ├── SalaryStrategy.java
│   │   ├── PermanentSalaryStrategy.java
│   │   ├── ContractSalaryStrategy.java
│   │   ├── SalesSalaryStrategy.java
│   │   └── ExecutiveSalaryStrategy.java
│   └── tax                                   # Tax regime strategies
│       ├── TaxStrategy.java
│       ├── OldTaxRegimeStrategy.java
│       └── NewTaxRegimeStrategy.java
│
├── repository                                # 4. DIP ABSTRACTIONS (Storage)
│   ├── EmployeeRepository.java
│   ├── InMemoryEmployeeRepository.java
│   ├── PayrollRepository.java
│   └── InMemoryPayrollRepository.java
│
├── notification                              # 4. DIP ABSTRACTIONS (Notifications)
│   ├── NotificationService.java
│   └── ConsoleNotificationService.java
│
├── service                                   # 5. SRP BUSINESS LOGIC
│   ├── DeductionService.java                 # Handles only statutory deductions (PF, Health Insurance)
│   └── PayrollService.java                   # Orchestrates calculation, tax, save, and notification
│
└── Main.java                                 # 6. RUNNER (Direct demonstration of bad vs. good)
```

---

## 2. SOLID Principle Mapping

| Principle | Bad Design (Before) | Refactored Design (After) |
| :--- | :--- | :--- |
| **SRP** (Single Responsibility) | `BadPayrollService` handled gross pay, taxes, deductions, database saving, and email notifications in one 60-line method. | Split into dedicated single-responsibility classes: `SalaryStrategy` (gross pay), `DeductionService` (deductions), `TaxStrategy` (tax calculation), `PayrollRepository` (persistence), and `NotificationService` (messaging). |
| **OCP** (Open/Closed) | `BadPayrollService` had hardcoded `if-else` blocks for `"PERMANENT"`, `"CONTRACT"`, and `"SALES"`. Adding `"FREELANCER"` meant editing existing code. | `PayrollService.registerSalaryStrategy(type, strategy)` allows adding new salary types dynamically at runtime without editing `PayrollService`. |
| **LSP** (Liskov Substitution) | `BadContractEmployee` overrode `getHraAllowance()` to throw `UnsupportedOperationException`, crashing client code at runtime. | Base `Employee` has only common fields. `ContractEmployee` safely substitutes `Employee` anywhere without exceptions. |
| **ISP** (Interface Segregation) | One bloated class forcing HRA, contract amounts, and executive bonuses onto all employees. | Split into lean, role-specific interfaces: `HasHra`, `HasCommission`, `HasExecutiveBenefits`. |
| **DIP** (Dependency Inversion) | `BadPayrollService` directly called hardcoded database and email logic. | `PayrollService` depends on `EmployeeRepository`, `PayrollRepository`, and `NotificationService` abstractions injected via constructor. |

---

## 3. Test Suite Verification

Run tests with Maven:
```powershell
mvn test
```

Results:
```
[INFO] Running com.company.payroll.SolidPayrollTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
