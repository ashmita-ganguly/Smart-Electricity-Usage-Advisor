package smartenergy.utility;

import java.util.Scanner;

/**
 * Provides robust input validation for the application.
 * All methods loop until valid input is received, preventing
 * crashes from invalid user input (NumberFormatException, etc.).
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class InputValidator {

    private Scanner sc;

    /**
     * Constructs an InputValidator with the given Scanner.
     *
     * @param sc the Scanner instance for reading user input
     */
    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Prompts the user for a positive double value.
     * Repeats until a valid number greater than 0 is entered.
     *
     * @param message the prompt message to display
     * @return a positive double value
     */
    public double getPositiveDouble(String message) {

        while (true) {

            System.out.print(message);

            try {

                double value =
                        Double.parseDouble(sc.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.println(
                        "Please enter a value greater than 0.");

            } catch (Exception e) {

                System.out.println(
                        "Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Prompts the user for hours (0 to 24 range).
     * Repeats until a valid number within range is entered.
     *
     * @param message the prompt message to display
     * @return hours value between 0 and 24 (inclusive)
     */
    public double getHours(String message) {

        while (true) {

            System.out.print(message);

            try {

                double hours =
                        Double.parseDouble(sc.nextLine());

                if (hours >= 0 && hours <= 24) {
                    return hours;
                }

                System.out.println(
                        "Hours should be between 0 and 24.");

            } catch (Exception e) {

                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Prompts the user for a positive integer.
     * Repeats until a valid integer greater than 0 is entered.
     *
     * @param message the prompt message to display
     * @return a positive integer value
     */
    public int getPositiveInt(String message) {

        while (true) {

            System.out.print(message);

            try {

                int value =
                        Integer.parseInt(sc.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.println(
                        "Enter a number greater than 0.");

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid integer.");
            }
        }
    }

    /**
     * Prompts the user for an integer within a specific range.
     *
     * @param message the prompt message
     * @param min     minimum allowed value (inclusive)
     * @param max     maximum allowed value (inclusive)
     * @return integer within the specified range
     */
    public int getIntInRange(String message, int min, int max) {

        while (true) {

            System.out.print(message);

            try {

                int value =
                        Integer.parseInt(sc.nextLine());

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Enter a number between " + min
                        + " and " + max + ".");

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid integer.");
            }
        }
    }
}
