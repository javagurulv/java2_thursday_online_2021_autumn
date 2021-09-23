package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddMenuValidator {

    public List<CoreError> validate(AddMenuRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(AddMenuRequest request) {
        return ( request.getTitle() == null || request.getTitle().isEmpty() )
                ? Optional.of(new CoreError("title", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDescription(AddMenuRequest request) {
        return ( request.getDescription() == null || request.getDescription().isEmpty() )
                ? Optional.of(new CoreError("description", "Must not be empty!"))
                : Optional.empty();
    }

        private Optional<CoreError> validatePrice(AddMenuRequest request) {
        return (request.getPrice() == 0.00)
                ? Optional.of(new CoreError("price", "Must not be empty!"))
                : Optional.empty();
    }

    }
