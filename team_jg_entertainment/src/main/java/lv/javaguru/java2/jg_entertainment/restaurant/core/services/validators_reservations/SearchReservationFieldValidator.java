package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.SearchReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchReservationFieldValidator {

    public List<CoreError> validatorField(SearchReservationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getReservationId()) && isEmpty(request.getUsedId())
                && isEmpty(request.getDate())) {
            errors.add(new CoreError("reservationId", "can not be empty!"));
            errors.add(new CoreError("userId", "can not be empty!"));
            errors.add(new CoreError("date", "can not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null
                || str.isEmpty();
    }
}
