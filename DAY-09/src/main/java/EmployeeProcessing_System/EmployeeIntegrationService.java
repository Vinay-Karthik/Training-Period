package EmployeeProcessing_System;

public class EmployeeIntegrationService {

    // Simulates validating and sending one employee to an external payroll API.
    public void integrate(Employee employee, int simulatedApiDelayMs) {
        validate(employee);

        try {
            // Simulated network wait: this is an I/O-bound workload.
            Thread.sleep(simulatedApiDelayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Integration interrupted for " + employee.id(), e);
        }

        // In a real service, this is where the payroll API response is handled.
    }

    private void validate(Employee employee) {
        if (employee.name() == null || employee.name().isBlank()
                || employee.email() == null || !employee.email().contains("@")) {
            throw new IllegalArgumentException("Invalid employee: " + employee.id());
        }
    }
}
