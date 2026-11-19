package com.company.payroll.notification;

import com.company.payroll.model.Employee;
import com.company.payroll.model.PayrollResult;

/**
 * Concrete notification implementation logging to console.
 */
public class ConsoleNotificationService implements NotificationService {

    @Override
    public void notifyPayroll(Employee employee, PayrollResult result) {
        System.out.printf("[Notification] Sent pay slip to %-25s | Net Pay: $%,.2f%n",
                employee.getEmail(), result.getNetSalary());
    }
}
