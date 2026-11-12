package model;

public record PayrollResult(
    String employeeId,
    double grossSalary,
    double taxDeduction,
    double otherDeductions,
    double netSalary,
    boolean success,
    String errorMessage
) {
    public static PayrollResult success(String employeeId, double gross, double tax, double other, double net) {
        return new PayrollResult(employeeId, gross, tax, other, net, true, null);
    }

    public static PayrollResult failure(String employeeId, String errorMessage) {
        return new PayrollResult(employeeId, 0.0, 0.0, 0.0, 0.0, false, errorMessage);
    }
}
