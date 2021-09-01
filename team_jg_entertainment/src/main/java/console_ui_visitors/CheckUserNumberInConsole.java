package console_ui_visitors;

import java.util.Scanner;

public class CheckUserNumberInConsole {

    private static int getNumberMenuInConsole() {
        System.out.println("Please, choose number of menu: ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public int getCorrectNumberInConsole(int minNumber, int maxNumber) {
        int getUserNumber = -1;
        try {
            getUserNumber = getNumberMenuInConsole();
            if (getUserNumber < minNumber || getUserNumber > maxNumber) {
                throw new Exception("Your number isn't correct, please try to press correct number from menu: ");//NumberFormatException();
            }
        } catch (Exception formatException) {
            System.out.println(formatException.getMessage());
        }
        return getUserNumber;
    }
}
