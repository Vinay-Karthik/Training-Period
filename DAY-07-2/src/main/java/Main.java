import model.Employee;
import model.PayrollResult;
import repository.EmployeeRepository;
import service.*;
import task.EmployeeTaskConsumer;
import task.EmployeeTaskProducer;
import util.PayrollDataProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("==================================================");
            System.out.println("  CONCURRENT PAYROLL PROCESSING ENGINE INITIALIZED");
            System.out.println("==================================================");

            EmployeeRepository repository = new EmployeeRepository();
            List<Employee> employeeList = PayrollDataProvider.generateEmployees(100);
            employeeList.forEach(repository::addEmployee);

            RaceConditionDemo demo = new RaceConditionDemo();
            demo.runDemo(10000);

            PayrollService payrollService = new PayrollService();
            ConcurrentPayrollService concurrentService = new ConcurrentPayrollService(payrollService);

            int workerThreads = 5;
            long startTime = System.currentTimeMillis();
            List<PayrollResult> results = concurrentService.processBatch(repository.findAll(), workerThreads);
            long durationMs = System.currentTimeMillis() - startTime;

            PayrollReportService reportService = new PayrollReportService();
            PayrollReportService.ReportSummary summary = reportService.generateSummaryAsync(results).get();

            System.out.println("\nCONCURRENT PAYROLL PROCESSING REPORT");
            System.out.println("--------------------------------------------------");
            System.out.printf("Total Employees:         %d%n", repository.size());
            System.out.printf("Worker Threads:          %d%n", workerThreads);
            System.out.printf("Successfully Processed:  %d%n", concurrentService.getSuccessCount());
            System.out.printf("Failed:                  %d%n", concurrentService.getFailureCount());
            System.out.printf("Audited Lock Records:    %d%n", concurrentService.getAuditedRecordCount());
            System.out.printf("Total Gross Salary:      $%,.2f%n", summary.totalGross());
            System.out.printf("Total Tax:               $%,.2f%n", summary.totalTax());
            System.out.printf("Total Deductions:        $%,.2f%n", summary.totalDeductions());
            System.out.printf("Total Net Salary:        $%,.2f%n", summary.totalNet());
            System.out.printf("Processing Duration:     %d ms%n", durationMs);
            System.out.println("--------------------------------------------------");

            System.out.println("\n--- Volatile Visibility Demonstration ---");
            new VolatileDemo().runDemo();

            System.out.println("\n--- Deadlock Demonstration ---");
            DeadlockDemo.runDemo();

            System.out.println("\n--- Starvation Prevention Demonstration ---");
            new StarvationPreventionDemo().runDemo();

            System.out.println("\n--- Executing Producer-Consumer Queue Pipeline ---");
            BlockingQueue<Employee> queue = new ArrayBlockingQueue<>(20);
            List<PayrollResult> pcResults = Collections.synchronizedList(new java.util.ArrayList<>());

            int consumerCount = 2;
            Thread producerThread = new Thread(
                    new EmployeeTaskProducer(queue, employeeList.subList(0, 10), consumerCount), "Producer-1");
            List<Thread> consumers = new java.util.ArrayList<>();
            for (int i = 0; i < consumerCount; i++) {
                consumers.add(new Thread(
                        new EmployeeTaskConsumer(queue, payrollService, pcResults), "Consumer-" + (i + 1)));
            }

            producerThread.start();
            consumers.forEach(Thread::start);
            producerThread.join();
            for (Thread consumer : consumers) consumer.join();

            System.out.printf("Producer-Consumer completed. Records processed in queue pipeline: %d%n", pcResults.size());
            System.out.println("Payroll processing completed successfully.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Application interrupted; shutdown requested.");
        } catch (Exception e) {
            System.err.println("Execution failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
