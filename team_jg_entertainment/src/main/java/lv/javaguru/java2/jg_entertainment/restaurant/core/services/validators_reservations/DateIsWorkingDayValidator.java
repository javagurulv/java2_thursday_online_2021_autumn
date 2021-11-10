package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;


import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateIsWorkingDayValidator implements DateValidator {

    private GetReservationDate getReservationDate;

    public DateIsWorkingDayValidator(GetReservationDate getReservationDate) {
        this.getReservationDate = getReservationDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime reservationDate = getReservationDate.getReservationDateFromString(date);
        boolean dateIsWorkingDay = reservationDate.getDayOfWeek().getValue() >= 1  &&
                reservationDate.getDayOfWeek().getValue() <= 5;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "There is no show at that date!"));
        }
    }
}
