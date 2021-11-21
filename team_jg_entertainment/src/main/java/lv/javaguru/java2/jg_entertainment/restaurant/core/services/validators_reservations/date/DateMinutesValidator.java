package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.date;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateMinutesValidator implements DateValidator {
    private GetReservationDate getReservationDate;

    public DateMinutesValidator(GetReservationDate getReservationDate) {
        this.getReservationDate = getReservationDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime visitTime = getReservationDate.getReservationDateFromString(date);
        boolean visit = visitTime.getMinute() == 0;
        if (visit) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Minutes", "is not correct!"));
        }
    }
}
