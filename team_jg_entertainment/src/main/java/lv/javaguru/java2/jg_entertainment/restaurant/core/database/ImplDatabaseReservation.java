package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImplDatabaseReservation implements DatabaseReservation {

    List<Reservation> reservationList = new ArrayList<>();

    @Override
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    @Override
    public boolean removeReservation(Long idReservation) {
        boolean deleteReservationInformation = false;

        Optional<Reservation> optionalReservation = reservationList.stream()
                .filter(reservation -> reservation.getReservationID().equals(idReservation))
                .findFirst();
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            deleteReservationInformation = reservationList.remove(reservation);
        }
        return deleteReservationInformation;
    }
    //reservationList.removeIf(reservation ->reservation.getReservationID().equals(idReservation))

    @Override
    public List<Reservation> showReservationList() {
        return reservationList;
    }
}
