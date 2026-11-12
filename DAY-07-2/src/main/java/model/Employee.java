package model;

import java.util.Objects;

public record Employee(
    String id,
    String name,
    String department,
    String designation,
    double grossSalary
) {
    public Employee {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be null or blank");
        }
        if (grossSalary < 0) {
            throw new IllegalArgumentException("Gross salary must be non-negative");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
