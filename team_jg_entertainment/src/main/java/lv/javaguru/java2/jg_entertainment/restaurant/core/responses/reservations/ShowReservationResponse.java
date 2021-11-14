package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.util.List;

public class ShowReservationResponse extends CoreResponse{
    private List<Reservation> reservations;

    public ShowReservationResponse(List<Reservation> reservations) {
       this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
