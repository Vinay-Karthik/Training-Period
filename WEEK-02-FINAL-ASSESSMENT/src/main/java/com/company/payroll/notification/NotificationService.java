package com.company.payroll.notification;

import com.company.payroll.model.Employee;
import com.company.payroll.model.PayrollResult;

/**
 * DIP: Abstraction for notifications. Can be swapped with EmailNotification,
 * SmsNotification, or MockNotification without changing business logic.
 */
public interface NotificationService {
    void notifyPayroll(Employee employee, PayrollResult result);
}
