package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddTableValidator {

    public List<CoreError> validate(AddTableRequest addTableRequest) {
        List<CoreError> coreErrors = new ArrayList<>();
        tableTitleValidator(addTableRequest).ifPresent(coreErrors::add);
        tablePriceValidator(addTableRequest).ifPresent(coreErrors::add);
        tableCapacityValidator(addTableRequest).ifPresent(coreErrors::add);
        return coreErrors;
    }

    private Optional<CoreError> tableTitleValidator(AddTableRequest addTableRequest) {
        return (addTableRequest.getTitle() == null || addTableRequest.getTitle().isEmpty())
                ? Optional.of(new CoreError("table title", "Shouldn't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> tableCapacityValidator(AddTableRequest addTableRequest) {
        return (addTableRequest.getTableCapacity() <= 0
                || addTableRequest.getTableCapacity() > 15)
                ? Optional.of(new CoreError("table capacity", "Shouldn't be 0 or less and more than 15"))
                : Optional.empty();
    }

    private Optional<CoreError> tablePriceValidator(AddTableRequest addTableRequest) {
        return (addTableRequest.getPrice() <= 0)
                ? Optional.of(new CoreError("table price", "Shouldn't be empty or negative (less than 0)"))
                : Optional.empty();
    }
}
