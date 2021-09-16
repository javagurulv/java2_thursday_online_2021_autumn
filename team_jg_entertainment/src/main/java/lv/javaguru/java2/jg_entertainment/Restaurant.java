package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.AddTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.GetAllTablesUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.RemoveTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.SearchVisitorsRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorFindVisitor;

public class Restaurant {

    private static final DatabaseVisitors databaseRestaurant = new ImplDatabaseVisitors();

    private static ValidatorAddVisitor validator = new ValidatorAddVisitor();
    private static ValidatorDeleteVisitor validatorDelete = new ValidatorDeleteVisitor();
    private static ValidatorFindVisitor validatorFindVisitor = new ValidatorFindVisitor();
    private static SearchVisitorsRequestValidator searchVisitorsRequestValidator = new SearchVisitorsRequestValidator();

    private static ServiceAddAllVisitors addVisitorsService = new ServiceAddAllVisitors(databaseRestaurant, validator);
    private static ServiceDeleteVisitors deleteVisitorsService = new ServiceDeleteVisitors(databaseRestaurant, validatorDelete);
    private static ServiceFindByIdVisitors findByIdVisitors = new ServiceFindByIdVisitors(databaseRestaurant, validatorFindVisitor);
    private static ServiceShowListVisitors showAllVisitorsService = new ServiceShowListVisitors(databaseRestaurant);
    private static SearchVisitorsService searchVisitorsService = new SearchVisitorsService(databaseRestaurant, searchVisitorsRequestValidator);

    private static RestaurantUIAction uiAddVisitorAction = new UIActionAddVisitors(addVisitorsService);
    private static RestaurantUIAction uiDeleteVisitorsAction = new UIActionDeleteVisitors(deleteVisitorsService);
    private static RestaurantUIAction uiFindIdVisitors = new UIActionFindVisitors(findByIdVisitors);
    private static RestaurantUIAction uiGetAllVisitorsAction = new UIActionShowListWithAllVisitors(showAllVisitorsService);
    private static RestaurantUIAction searchUIAction = new SearchVisitorsUIAction(searchVisitorsService);

    private static CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();
    private static RestaurantUIAction uiExitAction = new UIActionExit();

    //menu
    private static DatabaseMenu databaseMenu = new ImplDatabaseMenu();
    private static AddMenuService addMenuService = new AddMenuService(databaseMenu);
    private static RemoveMenuService removeMenuService = new RemoveMenuService(databaseMenu);
    private static GetAllMenusService getAllMenusService = new GetAllMenusService(databaseMenu);
    private static UIAction addMenuUIAction = new AddMenuUIAction(addMenuService);
    private static UIAction removeMenuUIAction = new RemoveMenuUIAction(removeMenuService);
    private static UIAction getAllMenusUIAction = new GetAllMenusUIAction(getAllMenusService);
    private static UIAction exitUIAction = new ExitUIAction();

    ///table
    private static TableDatabase tableDatabase = new ImplDatabaseTable();
    private static AddTableService addTableService = new AddTableService(tableDatabase);
    private static RemoveTableService removeTableService = new RemoveTableService(tableDatabase);
    private static GetAllTablesService getAllTablesService = new GetAllTablesService(tableDatabase);
    private static lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction addTableUIAction = new AddTableUIAction(addTableService);
    private static lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction removeTableUIAction = new RemoveTableUIAction(removeTableService);
    private static lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction getAllTablesUIAction = new GetAllTablesUIAction(getAllTablesService);
    private static UIAction exitUIActionTables = new ExitUIAction();//

    public static void main(String[] args) {
        while (true) { //menuPrint();
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
        System.out.println("3. Find ID visitor's in catalogue-> ");
        System.out.println("4. Show all visitor's in base of restaurant->");
        System.out.println("5. Search visitor's->");
        System.out.println("6. Exit! ");
        System.out.println();
        int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 6);
        executeVisitorMenu(numberOfMenu);
        return numberOfMenu;
    }

    private static void executeVisitorMenu(int numberOfMenu) {
        switch (numberOfMenu) {
            case 1: {
                uiAddVisitorAction.execute();
                break;
            }
            case 2: {
                uiDeleteVisitorsAction.execute();
                break;
            }
            case 3: {
                uiFindIdVisitors.execute();
                break;
            }
            case 4: {
                uiGetAllVisitorsAction.execute();
                break;
            }
            case 5: {
                searchUIAction.execute();
            }
            case 6: {
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
                exitUIActionTables.execute();
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
                addTableUIAction.execute();
                break;
            }
            case 2: {
                removeTableUIAction.execute();
                break;
            }
            case 3: {
                getAllTablesUIAction.execute();
                break;
            }
            case 4: {
                exitUIAction.execute();
                break;
            }
        }
    }

    private static int checkNumberOfMenu() {
        System.out.println("Press enter number from menu !");
        return checkNumber.getCorrectNumberMenu(1, 4);

    }
}
