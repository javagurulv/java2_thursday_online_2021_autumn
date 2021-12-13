package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetMenuValidator {

    public List<CoreError> validate(GetMenuRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetMenuRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("id", "must not be empty!"))
                : Optional.empty();
    }
}
