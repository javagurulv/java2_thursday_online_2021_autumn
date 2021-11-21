package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CheckMenuNumberFromConsole {

    private static int getNumberMenuFromConsole() {
        System.out.println("number--> ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public int getCorrectNumberMenu(int minNumber, int maxNumber) {
        int getUserNumber = -1;
        try {
            getUserNumber = getNumberMenuFromConsole();
            if (getUserNumber < minNumber || getUserNumber > maxNumber) {
                throw new Exception("Your number isn't correct, please try to press correct number from menu: ");
            }
        } catch (Exception formatException) {
            System.out.println(formatException.getMessage());
        }
        return getUserNumber;
    }
}
