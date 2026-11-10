package model;

import java.security.SecureRandom;

public final class AccountNumberGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private AccountNumberGenerator() {
    }

    public static String generateAccountNumber() {

        long number = 1_000_000L
                + RANDOM.nextInt(9_000_000);

        return String.valueOf(number);
    }
}
