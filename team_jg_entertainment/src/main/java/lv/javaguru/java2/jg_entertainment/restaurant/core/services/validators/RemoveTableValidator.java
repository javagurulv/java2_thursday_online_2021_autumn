package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveTableValidator {

    public List<CoreError> coreErrors(RemoveTableRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        validateByID(request).ifPresent(errorList::add);
        return errorList;
    }

    private Optional<CoreError> validateByID(RemoveTableRequest request) {
        return (request.getTableIdToRemove() == null)
                ? Optional.of(new CoreError("idTable", "Can't be null"))
                : Optional.empty();
    }
}
