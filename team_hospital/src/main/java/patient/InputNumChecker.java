package patient;

import java.util.Scanner;

public class InputNumChecker {
    public int execute(int inputMin, int inputMax) {
        Scanner scanner = new Scanner(System.in);
        int input = askInt();
        if (input < inputMin || input > inputMax) {
            boolean check = false;
            while (!check) {
                System.out.println("Please enter number from " + inputMin
                        + " to " + inputMax + ".");
                input = scanner.nextInt();
                if (input >= inputMin && input <= inputMax) {
                    check = true;
                }
            }
        }
        return input;
    }

    private int askInt() {
        Scanner scanner = new Scanner(System.in);
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = Integer.parseInt(scanner.nextLine());
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
