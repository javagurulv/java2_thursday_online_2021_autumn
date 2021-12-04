package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.util.List;

public class SearchReservationResponse extends CoreResponse {

    private List<Reservation> reservations;

    public SearchReservationResponse(List<Reservation> reservations, List<CoreError> errorList) {
        super(errorList);
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
