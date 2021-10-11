package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.util.List;

public class AddReservationResponse extends CoreResponse {

    private Reservation reservation;

    public AddReservationResponse(Reservation reservation) {
        this.reservation = reservation;
    }

    public AddReservationResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public Reservation getReservation() {
        return reservation;
    }
}
