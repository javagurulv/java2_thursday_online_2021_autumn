package lv.javaguru.java2.jg_entertainment.restaurant;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.visitors_services.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.visitors_services.ServiceDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.visitors_services.ServiceFindByIdVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.visitors_services.ServiceShowListVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.AddVisitorValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DeleteVisitorValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.FindVisitorValidator;

public class Restaurant {

    private static final DatabaseVisitors databaseRestaurant = new ImplDatabaseRestaurant();

    private static AddVisitorValidator validator = new AddVisitorValidator();
    private static final ServiceAddAllVisitors addVisitorsService = new ServiceAddAllVisitors(databaseRestaurant, validator);
    private static final RestaurantUIAction uiAddVisitorAction = new UIActionAddVisitors(addVisitorsService);
    private static DeleteVisitorValidator validatorDelete = new DeleteVisitorValidator();
    private static final ServiceDeleteVisitors deleteVisitorsService = new ServiceDeleteVisitors(databaseRestaurant, validatorDelete);
    private static final RestaurantUIAction uiDeleteVisitorsAction = new UIActionDeleteVisitors(deleteVisitorsService);
    private static FindVisitorValidator validatorFindVisitor = new FindVisitorValidator();
    private static final ServiceFindByIdVisitors findByIdVisitors = new ServiceFindByIdVisitors(databaseRestaurant,validatorFindVisitor);
    private static final RestaurantUIAction uiFindIdVisitors = new UIActionFindByIdVisitors(findByIdVisitors);

    private static final ServiceShowListVisitors showAllVisitorsService = new ServiceShowListVisitors(databaseRestaurant);
    private static final RestaurantUIAction uiGetAllVisitorsAction = new UIActionShowListAllVisitors(showAllVisitorsService);


    private static final CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();
    private static final RestaurantUIAction uiExitAction = new UIActionExit();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumber.getCorrectNumberMenu(1, 5);
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
        System.out.println("5. Exit! ");
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
                uiExitAction.execute();
                break;
            }
        }
    }
}
