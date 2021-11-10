package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseReservation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateExistenceValidator implements DateValidator {

    private GetReservationDate getReservationDate;
    private DatabaseReservation database;

    public DateExistenceValidator(GetReservationDate getReservationDate, DatabaseReservation database) {
        this.getReservationDate = getReservationDate;
        this.database = database;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime dateTime = getReservationDate.getReservationDateFromString(date);
        for ( Reservation d : database.getAllReservations()) {
            if (d.getReservationDate().isEqual(dateTime)) {
                return Optional.of(new CoreError("Date", "all is reserved"));
            }
        }
        return Optional.empty();
    }
}
