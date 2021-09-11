package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import java.util.Scanner;

public class CheckMenuNumberFromConsole {

    private static int getNumberMenuFromConsole() {
        System.out.println("Please, choose number of menu: ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public int getCorrectNumberMenu(int minNumber, int maxNumber) {
        int getUserNumber = -1;
        try {
            getUserNumber = getNumberMenuFromConsole();
            if (getUserNumber < minNumber || getUserNumber > maxNumber) {
                throw new Exception("Your number isn't correct, please try to press correct number from menu: ");//NumberFormatException();
            }
        } catch (Exception formatException) {
            System.out.println(formatException.getMessage());
        }
        return getUserNumber;
    }
}
