package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.AddTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.ExitTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.GetAllTablesUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.RemoveTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;

public class RestaurantAction {

    private static ApplicationContext applicationContext = new ApplicationContext();
    private static CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumberOfMenu();
            switch (numberOfMenu) {
                case 1 -> visitorsMenu();
                case 2 -> restaurantMenu();
                case 3 -> tablesMenu();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Hello ! MENU: ");
        System.out.println("1--> choose action with visitors !");
        System.out.println("2--> choose action with restaurant menu !");
        System.out.println("3--> choose action with tables !");
        System.out.println("4--> EXIT !");
        System.out.println();
    }

    private static int visitorsMenu() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Visitors program menu, press number what do you want to do! ");
        System.out.println("1. Add information about visitor to list of restaurant-> ");
        System.out.println("2. Enter ID visitor that delete from list of restaurant-> ");
        System.out.println("3. Show all visitor's in base of restaurant->");
        System.out.println("4. Search visitor's in catalogue->");
        System.out.println("5. Exit! ");
        System.out.println();
        int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 5);
        executeVisitorMenu(numberOfMenu);
        return numberOfMenu;
    }

    private static void executeVisitorMenu(int numberOfMenu) {
        switch (numberOfMenu) {
            case 1: {
                UIActionAddVisitors uiAddVisitorAction = applicationContext.getBean(UIActionAddVisitors.class);
                uiAddVisitorAction.execute();
                break;
            }
            case 2: {
                UIActionDeleteVisitors uiDeleteVisitorsAction = applicationContext.getBean(UIActionDeleteVisitors.class);
                uiDeleteVisitorsAction.execute();
                break;
            }
            case 3: {
                UIActionShowListWithAllVisitors uiGetAllVisitorsAction = applicationContext.getBean(UIActionShowListWithAllVisitors.class);
                uiGetAllVisitorsAction.execute();
                break;
            }
            case 4: {
                SearchVisitorsUIAction searchUIAction = applicationContext.getBean(SearchVisitorsUIAction.class);
                searchUIAction.execute();
            }
            case 5: {
                UIActionExit uiExitAction = applicationContext.getBean(UIActionExit.class);
                uiExitAction.execute();
                break;
            }
        }
    }

    private static int restaurantMenu() {
        System.out.println("Restaurant menu:");
        System.out.println("1. Add menu to list");
        System.out.println("2. Delete menu from list");
        System.out.println("3. Show all menus in the list");
        System.out.println("4. Exit");
        System.out.println("");
        int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 4);
        executeSelectedRestaurantMenu(numberOfMenu);
        return numberOfMenu;
    }

    private static void executeSelectedRestaurantMenu(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                AddMenuUIAction addMenuUIAction = applicationContext.getBean(AddMenuUIAction.class);
                addMenuUIAction.execute();
                break;
            }
            case 2: {
                RemoveMenuUIAction removeMenuUIAction = applicationContext.getBean(RemoveMenuUIAction.class);
                removeMenuUIAction.execute();
                break;
            }
            case 3: {
                GetAllMenusUIAction getAllMenusUIAction = applicationContext.getBean(GetAllMenusUIAction.class);
                getAllMenusUIAction.execute();
                break;
            }
            case 4: {
                ExitUIAction exitUIActionMenu = applicationContext.getBean(ExitUIAction.class);
                exitUIActionMenu.execute();
                break;
            }
        }
    }

    private static int tablesMenu() {
        System.out.println();
        System.out.println("Tables menu:");
        System.out.println("1. Add table to list");
        System.out.println("2. Delete table from list");
        System.out.println("3. Show all tables in the list");
        System.out.println("4. Exit");
        System.out.println();
        int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 4);
        executeSelectedTabledMenu(numberOfMenu);
        return numberOfMenu;
    }

    private static void executeSelectedTabledMenu(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                AddTableUIAction addTableUIAction = applicationContext.getBean(AddTableUIAction.class);
                addTableUIAction.execute();
                break;
            }
            case 2: {
                RemoveTableUIAction removeTableUIAction = applicationContext.getBean(RemoveTableUIAction.class);
                removeTableUIAction.execute();
                break;
            }
            case 3: {
                GetAllTablesUIAction getAllTablesUIAction = applicationContext.getBean(GetAllTablesUIAction.class);
                getAllTablesUIAction.execute();
                break;
            }
            case 4: {
                ExitTableUIAction exitTableUIAction = applicationContext.getBean(ExitTableUIAction.class);
                exitTableUIAction.execute();
                break;
            }
        }
    }

    private static int checkNumberOfMenu() {
        System.out.println("Start by choosing the program menu!");
        return checkNumber.getCorrectNumberMenu(1, 4);

    }
}
