package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteReservationValidator {

    @Autowired private ExistReservationValidator validator;

    public List<CoreError> validate(DeleteReservationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateExist(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteReservationRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("idReservation", "must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateExist(DeleteReservationRequest request) {
        return request.getId() == null
                ? Optional.empty()
                : validator.validate(request.getId());
    }
}
