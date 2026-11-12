package task;

import model.PayrollResult;

public class NotificationRunnable implements Runnable {
    private final PayrollResult payrollResult;

    public NotificationRunnable(PayrollResult payrollResult) {
        this.payrollResult = payrollResult;
    }

    @Override
    public void run() {
        if (payrollResult.success()) {
            System.out.printf("[Notification Thread %s] Payslip notification queued for Employee ID: %s%n",
                    Thread.currentThread().getName(), payrollResult.employeeId());
        }
    }
}
