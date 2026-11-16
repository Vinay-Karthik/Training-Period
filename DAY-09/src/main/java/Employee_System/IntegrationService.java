package Employee_System;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntegrationService {

    // Simulates fetching from HR API (50ms) + saving to Database (50ms)
    private Employee processEmployee(int id) {
        try {
            Thread.sleep(50); // Simulate HR API call
            String name = "Employee-" + id;
            String dept = (id % 2 == 0) ? "Engineering" : "Sales";

            Thread.sleep(50); // Simulate Database save
            return new Employee(id, name, dept);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // Implementation 1: Traditional Fixed Thread Pool
    public void runWithThreadPool(List<Integer> employeeIds, int poolSize) {
        try (ExecutorService executor = Executors.newFixedThreadPool(poolSize)) {
            for (int id : employeeIds) {
                executor.submit(() -> processEmployee(id));
            }
        } // Automatically waits for all tasks to finish
    }

    // Implementation 2: Virtual Threads
    public void runWithVirtualThreads(List<Integer> employeeIds) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int id : employeeIds) {
                executor.submit(() -> processEmployee(id));
            }
        } // Automatically waits for all tasks to finish
    }
}
