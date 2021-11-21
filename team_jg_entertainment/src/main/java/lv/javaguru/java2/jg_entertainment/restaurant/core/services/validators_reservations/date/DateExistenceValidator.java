package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.date;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateExistenceValidator implements DateValidator {

    private GetReservationDate getReservationDate;
    private ReservationRepository database;

    public DateExistenceValidator(GetReservationDate getReservationDate, ReservationRepository database) {
        this.getReservationDate = getReservationDate;
        this.database = database;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime dateTime = getReservationDate.getReservationDateFromString(date);
        for (Reservation reservation : database.getAllReservations()) {
            if (reservation.getReservationDate().isEqual(dateTime)) {
                return Optional.of(new CoreError("Date", "all is reserved"));
            }
        }
        return Optional.empty();
    }
}
