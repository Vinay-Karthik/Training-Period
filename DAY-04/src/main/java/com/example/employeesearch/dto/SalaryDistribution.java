package com.example.employeesearch.dto;

public record SalaryDistribution(
        long countBelow5Lakh,
        long count5To10Lakh,
        long countAbove10Lakh
) {
}
