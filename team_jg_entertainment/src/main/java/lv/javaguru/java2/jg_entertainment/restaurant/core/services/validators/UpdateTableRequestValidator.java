package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.UpdateTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateTableRequestValidator {

    public List<CoreError> validate(UpdateTableRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateCapacity(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(UpdateTableRequest request) {
        return request.getNewTitle() == null || request.getNewTitle().isEmpty()
                ? Optional.of(new CoreError("newTitle", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCapacity(UpdateTableRequest request) {
        return request.getNewTableCapacity() == 0 || request.getNewTableCapacity() < 0
                ? Optional.of(new CoreError("newTableCapacity", "must not be empty or less 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateTableRequest request) {
        return request.getNewPrice() == 0 || request.getNewPrice() < 0
                ? Optional.of(new CoreError("newPrice", "must not be empty or less 0!"))
                : Optional.empty();
    }
}
