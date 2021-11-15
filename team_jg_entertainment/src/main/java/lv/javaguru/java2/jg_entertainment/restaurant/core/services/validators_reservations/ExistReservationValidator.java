package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistReservationValidator {

    @Autowired private ReservationRepository repository;

    public Optional<CoreError> validate(Long id) {
        for (Reservation reservation : repository.getAllReservations()) {
            if (reservation.getIdReservation().equals(id)) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("reservation", "doesn't exist!"));
    }
}
