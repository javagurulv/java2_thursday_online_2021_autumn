package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import java.util.Scanner;

public class GetQueryWithConsole {

    public String getNumberFromConsole(String string){
        Scanner scanner = new Scanner(System.in);
        System.out.println(string);
        return scanner.nextLine();
    }
}
