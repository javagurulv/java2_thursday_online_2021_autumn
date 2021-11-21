package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class EnumValidator {

    public Optional<CoreError> enumValidate(String input) {
        try {
            EditReservationEnum.valueOf(input.toUpperCase(Locale.ROOT));
            return Optional.empty();
        } catch (IllegalArgumentException argumentException){
            return Optional.of(new CoreError("edit", "should be like ID_VISITOR, ID_TABLE, ID_MENU, RESERVATION_DATE"));
        }
    }
}
