package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.CheckMenuNumberFromConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramReservation {

    private Map<Integer, ReservationUIAction> menuNumberToUIActionMap;
    private CheckMenuNumberFromConsole checkNumberFromConsole = new CheckMenuNumberFromConsole();

    @Autowired
    public ProgramReservation(List<ReservationUIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        //menuNumberToUIActionMap.put(1, findReservationUIAction(uiActions, AddReservationUIAction.class));
        menuNumberToUIActionMap.put(2, findReservationUIAction(uiActions, ExitProgramListReservationUIAction.class));//return in main menu(RestaurantProgramList -> mainAction)
        menuNumberToUIActionMap.put(3, findReservationUIAction(uiActions, ExitReservationUIAction.class));
    }

    private ReservationUIAction findReservationUIAction(List<ReservationUIAction> reservationUIActions, Class uiActionClass) {
        return reservationUIActions.stream()
                .filter(reservationUIAction -> reservationUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printReservationMenu() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Reservation program menu, press number what do you want to do! ");
        //System.out.println("1. Add reservation->");
        System.out.println("2. Choose that return in MAIN MENU:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("3. Exit!");
        System.out.println();
    }

    public int getReservationMenuNumberFromUser() {
        return checkNumberFromConsole.getCorrectNumberMenu(1, 3);
    }

    public void executeSelectMenuItem(int selectMenu) {
        menuNumberToUIActionMap.get(selectMenu).execute();
    }
}
