package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.ReservationUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;

//@Component
public class ExitProgramListReservationUIAction implements ReservationUIAction {

    @Override
    public void execute() {
        System.out.println("You return in main menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
