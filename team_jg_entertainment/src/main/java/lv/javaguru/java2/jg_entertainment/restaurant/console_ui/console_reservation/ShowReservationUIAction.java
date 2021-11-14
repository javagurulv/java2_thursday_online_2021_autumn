package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ShowReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.ShowReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.ShowReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowReservationUIAction implements ReservationUIAction {

    @Autowired private ShowReservationService showReservationService;

    @Override
    public void execute() {
        System.out.println("reservation list: ");
        ShowReservationRequest request = new ShowReservationRequest();
        ShowReservationResponse response = showReservationService.execute(request);
        System.out.println(response.getReservations());
    }
}
