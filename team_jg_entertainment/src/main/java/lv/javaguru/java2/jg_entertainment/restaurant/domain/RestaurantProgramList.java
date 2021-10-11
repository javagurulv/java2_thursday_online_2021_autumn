package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.ProgramReservation;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestaurantProgramList {

    private static final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);

    private static CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();

    public void mainAction() {
        while (true) {
            printProgramMenu();
            int numberOfMenu = checkNumberOfMenu();
            switch (numberOfMenu) {
                case 1 -> visitorsMenu();
                case 2 -> restaurantMenu();
                case 3 -> tablesMenu();
                case 4 -> reservationMenu();
                case 5 -> System.exit(0);
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Hello ! MENU: ");
        System.out.println("1--> choose action with VISITORS: ");
        System.out.println("2--> choose action with restaurant MENU: ");
        System.out.println("3--> choose action with TABLES: ");
        System.out.println("4--> choose action with RESERVATION: ");
        System.out.println("5--> EXIT!");
        System.out.println();
    }

    private static void visitorsMenu() {
        ProgramMenuVisitor visitorProgram = applicationContext.getBean(ProgramMenuVisitor.class);
        while (true) {
            visitorProgram.print();
            int visitorNumberMenuInConsole = visitorProgram.getMenuNumberFromUser();
            visitorProgram.executeSelectMenuItem(visitorNumberMenuInConsole);
        }
    }

    private static void restaurantMenu() {
        ProgramMenuRestaurant programMenuRestaurant = applicationContext.getBean(ProgramMenuRestaurant.class);
        while (true) {
            programMenuRestaurant.printMenuInRestaurant();
            int menuNumberInConsole = programMenuRestaurant.getMenuNumberFromUser();
            programMenuRestaurant.executeSelectMenuItem(menuNumberInConsole);
        }
    }

    private static void tablesMenu() {
        ProgramMenuTable tableProgramMenu = applicationContext.getBean(ProgramMenuTable.class);
        while (true) {
            tableProgramMenu.printTableMenu();
            int tableNumberInConsole = tableProgramMenu.getMenuNumberFromUser();
            tableProgramMenu.executeSelectMenuItem(tableNumberInConsole);
        }
    }

    private static void reservationMenu() {
        ProgramReservation reservation = applicationContext.getBean(ProgramReservation.class);
        while (true) {
            reservation.printReservationMenu();
            int reservationNumberInConsole = reservation.getReservationMenuNumberFromUser();
            reservation.executeSelectMenuItem(reservationNumberInConsole);
        }
    }

    private static int checkNumberOfMenu() {
        System.out.println("Start by choosing the program menu!");
        return checkNumber.getCorrectNumberMenu(1, 5);
    }
}
