package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateTimeValidator implements DateValidator {
    private GetReservationDate getReservationDate;

    public DateTimeValidator(GetReservationDate getReservationDate) {
        this.getReservationDate = getReservationDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime reservationDate = getReservationDate.getReservationDateFromString(date);
        boolean dateIsWorkingDay = reservationDate.getHour() == 19;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Time in the date", "is not working hour!"));
        }
    }
}

