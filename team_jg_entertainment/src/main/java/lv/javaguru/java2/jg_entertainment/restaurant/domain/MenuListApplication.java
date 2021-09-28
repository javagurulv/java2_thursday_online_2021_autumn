package lv.javaguru.java2.jg_entertainment.restaurant.domain;
/*
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;

import java.util.Scanner;

public class MenuListApplication {

    private static DatabaseMenu databaseMenu = new ImplDatabaseMenu();
    private static AddMenuService addMenuService = new AddMenuService(databaseMenu);
    private static RemoveMenuService removeMenuService = new RemoveMenuService(databaseMenu);
    private static GetAllMenusService getAllMenusService = new GetAllMenusService(databaseMenu);
    private static UIAction addMenuUIAction = new AddMenuUIAction(addMenuService);
    private static UIAction removeMenuUIAction = new RemoveMenuUIAction(removeMenuService);
    private static UIAction getAllMenusUIAction = new GetAllMenusUIAction(getAllMenusService);
    private static UIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void printProgramMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add menu to list");
        System.out.println("2. Delete menu from list");
        System.out.println("3. Show all menus in the list");
        System.out.println("4. Exit");
        System.out.println("");
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                addMenuUIAction.execute();
                break;
            }
            case 2: {
                removeMenuUIAction.execute();
                break;
            }
            case 3: {
                getAllMenusUIAction.execute();
                break;
            }
            case 4: {
                exitUIAction.execute();
                break;

            }
        }
    }
}

*/