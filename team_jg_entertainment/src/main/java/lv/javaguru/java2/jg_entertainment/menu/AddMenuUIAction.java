package lv.javaguru.java2.jg_entertainment.menu;

import lv.javaguru.java2.jg_entertainment.Menu;

import java.util.Scanner;

public class AddMenuUIAction implements UIAction {

    private DatabaseMenu database;

    public AddMenuUIAction(DatabaseMenu database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        Long number = scanner.nextLong();
        Menu menu = new Menu(number);
        database.save(menu);
        System.out.println("Your menu was added to the order.");
    }
}