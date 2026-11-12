package task;

import model.Employee;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class EmployeeTaskProducer implements Runnable {
    public static final Employee POISON_PILL = new Employee(
            "__POISON__", "TERMINATE", "N/A", "N/A", 0.0);

    private final BlockingQueue<Employee> queue;
    private final List<Employee> employees;
    private final int consumerCount;

    public EmployeeTaskProducer(BlockingQueue<Employee> queue, List<Employee> employees, int consumerCount) {
        if (consumerCount <= 0) throw new IllegalArgumentException("consumerCount must be positive");
        this.queue = queue;
        this.employees = List.copyOf(employees);
        this.consumerCount = consumerCount;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        try {
            for (Employee employee : employees) {
                try {
                    queue.put(employee);
                } catch (InterruptedException e) {
                    interrupted = true;
                    break; // Stop producing, but still signal every consumer below.
                }
            }
        } finally {
            // Enqueue one termination marker per consumer. Complete this even if interrupted.
            for (int i = 0; i < consumerCount; i++) {
                boolean enqueued = false;
                while (!enqueued) {
                    try {
                        queue.put(POISON_PILL);
                        enqueued = true;
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
            }
            if (interrupted) Thread.currentThread().interrupt();
        }
    }
}
