package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveMenuRequestValidator {
    public List<CoreError> validate(RemoveMenuRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateMenuId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateMenuId(RemoveMenuRequest request) {
        return (request.getMenuNumberToRemove() == null)
                ? Optional.of(new CoreError("number", "Must not be empty!"))
                : Optional.empty();
    }
}
