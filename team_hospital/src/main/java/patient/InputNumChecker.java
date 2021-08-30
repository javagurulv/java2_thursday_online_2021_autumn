package patient;

import java.util.Scanner;

public class InputNumChecker {
    public int execute(int inputMin, int inputMax, int userInput) {
        Scanner scanner = new Scanner(System.in);
        int input = userInput;
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
}
