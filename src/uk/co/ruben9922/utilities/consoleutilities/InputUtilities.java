package uk.co.ruben9922.utilities.consoleutilities;

import java.util.Scanner;

public final class InputUtilities {
    private static final String EMPTY_STRING = "";

    public static int inputInt(Scanner scanner, String prompt, Integer min, Integer max, String errorMessage) {
        // Assign default values to parameters with null values
        if (prompt == null || errorMessage == null) {
            String rangeString = max != null ?
                    (min != null ? String.format("between %d and %d (inclusive)", min, max - 1) :
                            String.format("less than %d", max)) :
                    (min != null ? String.format("greater than or equal to %d", min) : EMPTY_STRING);
            if (prompt == null) {
                prompt = "Enter integer " + rangeString;
            }
            if (errorMessage == null) {
                errorMessage = "Only integers " + rangeString + " are allowed!";
            }
        }

        int result = 0;
        boolean inputValid;
        do {
            System.out.print(prompt);
            // Check whether integer in specified range was entered
            if (inputValid = scanner.hasNextInt()) {
                result = scanner.nextInt();
                inputValid = ((min == null || result >= min) && (max == null || result < max));
            } else {
                scanner.next();
            }

            // Display error message if input invalid
            if (!inputValid) {
                System.out.println(errorMessage);
                System.out.println();
            }
        } while (!inputValid);
        return result;
    }

    public static int inputInt(Scanner scanner, String prompt, Integer min, Integer max) {
        return inputInt(scanner, prompt, min, max, null);
    }

    public static int inputOptionInt(Scanner scanner, String[] options, String prompt) {
        // Assign default value to prompt parameter if null
        if (prompt == null) {
            prompt = String.format("Enter option [%d..%d]: ", 0, options.length - 1);
        }

        for (int i = 0; i < options.length; i++) {
            System.out.format("  %d: %s\n", i, options[i]);
        }
        System.out.println();

        return inputInt(scanner, prompt, 0, options.length, "Invalid option!");
    }

    public static int inputOptionInt(Scanner scanner, String[] options) {
        return inputOptionInt(scanner, options, null);
    }

    public static boolean inputYOrN(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toLowerCase().equals("y");
    }
}
