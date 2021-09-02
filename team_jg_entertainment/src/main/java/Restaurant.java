import console_ui_visitors.*;
import database.Database;
import database.ImplDatabaseRestaurant;
import service_visitors.ServiceAddAllVisitors;
import service_visitors.ServiceDeleteVisitors;
import service_visitors.ServiceFindByIdVisitors;
import service_visitors.ServiceShowListVisitors;

public class Restaurant {

    private static final Database databaseRestaurant = new ImplDatabaseRestaurant();

    private static final ServiceAddAllVisitors addVisitorsService = new ServiceAddAllVisitors(databaseRestaurant);
    private static final ServiceDeleteVisitors deleteVisitorsService = new ServiceDeleteVisitors(databaseRestaurant);
    private static final ServiceShowListVisitors showAllVisitorsService = new ServiceShowListVisitors(databaseRestaurant);
    private static final ServiceFindByIdVisitors findByIdVisitors = new ServiceFindByIdVisitors(databaseRestaurant);

    private static final RestaurantUIAction uiAddVisitorAction = new UIActionAddVisitors(addVisitorsService);
    private static final RestaurantUIAction uiDeleteVisitorsAction = new UIActionDeleteVisitors(deleteVisitorsService);
    private static final RestaurantUIAction uiFindIdVisitors = new UIActionFindByIdVisitors(findByIdVisitors);
    private static final RestaurantUIAction uiGetAllVisitorsAction = new UIActionShowListAllVisitors(showAllVisitorsService);

    private static final CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();
    private static final RestaurantUIAction uiExitAction = new UIActionExit();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumber.getCorrectNumberMenu(1,5);
            executeChooseMenu(numberOfMenu);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Start with program menu, press number what do you want to do. ");
        System.out.println("1. Add information about visitor to list of restaurant. ");
        System.out.println("2. Enter ID visitor that delete from list of restaurant. ");
        System.out.println("3. Find ID visitor's in catalogue. ");
        System.out.println("4. Show all visitor's in base of restaurant.");
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
