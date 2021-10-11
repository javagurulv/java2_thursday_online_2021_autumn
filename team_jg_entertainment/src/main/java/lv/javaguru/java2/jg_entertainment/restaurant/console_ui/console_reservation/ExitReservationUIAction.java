package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import org.springframework.stereotype.Component;

@Component
public class ExitReservationUIAction implements ReservationUIAction{

    @Override
    public void execute() {
        System.out.println("You finished! Thank a lot, have a good day !");
        System.exit(0);
    }
}
