package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservationLongNumChecker {
    public Optional<CoreError> validate(String input, String field) {
        try {
            Long.parseLong(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError(field, "must be a number!"));
        }
    }
}
