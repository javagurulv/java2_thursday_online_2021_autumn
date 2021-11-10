package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;

import java.time.LocalDateTime;
import java.util.Optional;

public class DateIsInFutureValidator implements DateValidator {
    private GetReservationDate getReservationDate;

    public DateIsInFutureValidator(GetReservationDate getReservationDate) {
        this.getReservationDate = getReservationDate;
    }
        @Override
        public Optional<CoreError> validate(String date){
            LocalDateTime reservationDate = getReservationDate.getReservationDateFromString(date);
            boolean dateIsInFuture = reservationDate.isAfter(LocalDateTime.now());
            if (dateIsInFuture) {
                return Optional.empty();
            } else {
                return Optional.of(new CoreError("Date", "is not in the future!"));
            }
        }
    }
