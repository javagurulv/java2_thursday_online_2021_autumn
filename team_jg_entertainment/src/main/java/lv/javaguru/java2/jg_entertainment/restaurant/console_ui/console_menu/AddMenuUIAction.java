package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;

import java.util.Scanner;

public class AddMenuUIAction implements UIAction {

    private DatabaseMenu database;

    public AddMenuUIAction(DatabaseMenu database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu title: ");
        String title = scanner.nextLine();
        System.out.println("Enter menu number: ");
        Long number = scanner.nextLong();
        System.out.println("Enter menu description: ");
        String description = scanner.nextLine();
        System.out.println("Enter menu price: ");
        int price = scanner.nextInt();
        Menu menu = new Menu(title,number, description,price);
        database.save(menu);
        System.out.println("Your menu was added to the order.");
    }
}