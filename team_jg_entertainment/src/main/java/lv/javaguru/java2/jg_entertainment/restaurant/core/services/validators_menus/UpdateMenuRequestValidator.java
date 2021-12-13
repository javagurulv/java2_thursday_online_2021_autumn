package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.UpdateMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateMenuRequestValidator {

    public List<CoreError> validate(UpdateMenuRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(UpdateMenuRequest request) {
        return request.getNewTitle() == null || request.getNewTitle().isEmpty()
                ? Optional.of(new CoreError("newTitle", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDescription(UpdateMenuRequest request) {
        return request.getNewDescription() == null || request.getNewDescription().isEmpty()
                ? Optional.of(new CoreError("newDescription", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateMenuRequest request) {
        return request.getNewPrice() == 0 || request.getNewPrice() < 0
                ? Optional.of(new CoreError("newPrice", "must not be empty or less than 0!"))
                : Optional.empty();
    }
}
