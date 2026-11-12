package task;

import model.Employee;
import model.PayrollResult;
import service.PayrollService;

import java.util.concurrent.Callable;

public class PayrollCallable implements Callable<PayrollResult> {
    private final Employee employee;
    private final PayrollService payrollService;

    public PayrollCallable(Employee employee, PayrollService payrollService) {
        this.employee = employee;
        this.payrollService = payrollService;
    }

    @Override
    public PayrollResult call() {
        return payrollService.calculatePayroll(employee);
    }
}
