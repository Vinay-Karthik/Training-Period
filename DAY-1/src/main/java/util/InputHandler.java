package util;


import java.math.BigDecimal;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // Read String
    public String readString(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isBlank()) {
                return input;
            }

            System.out.println("Input cannot be empty. Try again.");
        }
    }

    // Read Integer
    public int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException exception) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Read BigDecimal
    public BigDecimal readBigDecimal(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                BigDecimal value = new BigDecimal(input);

                if (value.compareTo(BigDecimal.ZERO) < 0) {

                    System.out.println(
                            "Value cannot be negative. Try again."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException exception) {

                System.out.println(
                        "Please enter a valid decimal number."
                );
            }
        }
    }

    // Close Scanner when application exits
    public void close() {
        scanner.close();
    }
}
