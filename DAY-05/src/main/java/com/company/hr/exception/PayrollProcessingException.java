package com.company.hr.exception;

public class PayrollProcessingException extends RuntimeException {
    public PayrollProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayrollProcessingException(String message) {
        super(message);
    }
}
