package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
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

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 6);
            executeChooseMenu(numberOfMenu);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Start with program menu, press number what do you want to do! ");
        System.out.println("1. Add information about visitor to list of restaurant-> ");
        System.out.println("2. Enter ID visitor that delete from list of restaurant-> ");
        System.out.println("3. Find ID visitor's in catalogue-> ");
        System.out.println("4. Show all visitor's in base of restaurant->");
        System.out.println("5. Search visitor's->");
        System.out.println("6. Exit! ");
        System.out.println();
    }

    private static void executeChooseMenu(int numberOfMenu) {
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
}
