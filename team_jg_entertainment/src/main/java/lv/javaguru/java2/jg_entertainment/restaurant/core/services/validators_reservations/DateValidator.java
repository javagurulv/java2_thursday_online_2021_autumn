package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;


import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;

import java.util.Optional;

public interface DateValidator {
    Optional<CoreError> validate(String date);

}
