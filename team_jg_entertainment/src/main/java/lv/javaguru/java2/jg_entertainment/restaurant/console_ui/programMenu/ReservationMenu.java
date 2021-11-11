package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.ProgramReservation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ReservationMenu implements Menu {
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramReservation reservationProgramMenu = applicationContext.getBean(ProgramReservation.class);
        while (true) {
            reservationProgramMenu.printReservationMenu();
            int reservationMenuNumber = reservationProgramMenu.getReservationMenuNumberFromUser();
            reservationProgramMenu.executeSelectMenuItem(reservationMenuNumber);
        }
    }
}
