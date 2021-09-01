import console_ui_visitors.*;
import database.Database;
import database.ImplDatabaseRestaurant;
import service_visitors.ServiceAddAllVisitors;
import service_visitors.ServiceDeleteVisitors;
import service_visitors.ServiceFindByIdVisitors;
import service_visitors.ServiceShowListVisitors;

import java.util.Scanner;

public class Restaurant {

    private static Database databaseRestaurant = new ImplDatabaseRestaurant();

    private static ServiceAddAllVisitors addVisitorsService = new ServiceAddAllVisitors(databaseRestaurant);
    private static ServiceDeleteVisitors deleteVisitorsService = new ServiceDeleteVisitors(databaseRestaurant);
    private static ServiceShowListVisitors showAllVisitorsService = new ServiceShowListVisitors(databaseRestaurant);
    private static ServiceFindByIdVisitors findByIdVisitors = new ServiceFindByIdVisitors(databaseRestaurant);

    private static RestaurantUIAction uiAddVisitorAction = new UIActionAddVisitors(addVisitorsService);
    private static RestaurantUIAction uiDeleteVisitorsAction = new UIActionDeleteVisitors(deleteVisitorsService);
    private static RestaurantUIAction uiFindIdVisitors = new UIActionFindByIdVisitors(findByIdVisitors);
    private static RestaurantUIAction uiGetAllVisitorsAction = new UIActionShowListAllVisitors(showAllVisitorsService);

    private static CheckUserNumberInConsole checkNumber = new CheckUserNumberInConsole();
    private static RestaurantUIAction uiExitAction = new UIActionExit();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumber.getCorrectNumberInConsole(1,5);
            executeChooseMenu(numberOfMenu);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Start with program menu, press number what do you want to do. ");
        System.out.println("1. Add visitor to list of restaurant. ");
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
