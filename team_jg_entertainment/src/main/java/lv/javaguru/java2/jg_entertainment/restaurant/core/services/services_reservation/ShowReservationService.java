package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ShowReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.ShowReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowReservationService {

    @Autowired private ReservationRepository database;

    public ShowReservationResponse execute(ShowReservationRequest request) {
        List<Reservation> reservationList = database.getAllReservations();
        return new ShowReservationResponse(reservationList);
    }
}
