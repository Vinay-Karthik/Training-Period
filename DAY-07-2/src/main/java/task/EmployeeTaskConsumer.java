package task;

import model.Employee;
import model.PayrollResult;
import service.PayrollService;
import task.EmployeeTaskProducer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class EmployeeTaskConsumer implements Runnable {
    private final BlockingQueue<Employee> queue;
    private final PayrollService payrollService;
    private final List<PayrollResult> processedResults;

    public EmployeeTaskConsumer(BlockingQueue<Employee> queue, PayrollService payrollService,
                                List<PayrollResult> processedResults) {
        this.queue = queue;
        this.payrollService = payrollService;
        this.processedResults = processedResults;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Employee employee = queue.take();
                if (employee.equals(EmployeeTaskProducer.POISON_PILL)) {
                    return; // Do not reinsert; producer supplies one pill per consumer.
                }
                PayrollResult result = payrollService.calculatePayroll(employee);
                processedResults.add(result); // Caller should supply a thread-safe list for multiple consumers.
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
