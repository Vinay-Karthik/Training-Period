package com.company.employee.model;

import java.util.Objects;

public class Designation {
    private final String title;

    public Designation(String title) {
        // if we use and operator we are getitng an null pointer expressoin due to the
        // short circuiting of the condition
        if (Objects.isNull(title) || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Designation title cannot be empty");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Designation designation)) return false;
        return title.equalsIgnoreCase(designation.title);
    }

    // we used hash ot check if one employee are not assigned with diff designations
    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase());
    }

    @Override
    public String toString() {
        return title;
    }
}
