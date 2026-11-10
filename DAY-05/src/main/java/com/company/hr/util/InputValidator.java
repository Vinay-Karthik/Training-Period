package com.company.hr.util;

import com.company.hr.exception.InvalidEmployeeDataException;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public static void validateEmployeeInput(String id, String name, String email, double salary) {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee name cannot be empty.");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmployeeDataException("Invalid email format: " + email);
        }
        if (salary < 0) {
            throw new InvalidEmployeeDataException("Salary cannot be negative: ₹" + salary);
        }
    }
}
