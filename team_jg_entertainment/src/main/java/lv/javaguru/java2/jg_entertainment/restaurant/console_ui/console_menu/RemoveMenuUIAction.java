package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.menu.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.UIAction;

import java.util.Scanner;

public class RemoveMenuUIAction implements UIAction {

    private DatabaseMenu database;

    public RemoveMenuUIAction(DatabaseMenu database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        Long number = Long.parseLong(scanner.nextLine());
        database.deleteByNr(number);
        System.out.println("That menu was removed from the order.");
    }
}
