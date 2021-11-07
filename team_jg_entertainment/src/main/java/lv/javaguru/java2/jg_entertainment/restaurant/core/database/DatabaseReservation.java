package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.util.List;

public interface DatabaseReservation {

    void addReservation(Reservation reservation);

    boolean removeReservation(Long idReservation);

    List<Reservation> showReservationList();

}
