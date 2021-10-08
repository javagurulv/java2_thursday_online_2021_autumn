package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidatorOrdering {

    public List<CoreError> validator(OrderingTable ordering) {
        List<CoreError> coreErrors = new ArrayList<>();
        validatorOrderBy(ordering).ifPresent(coreErrors::add);
        validatorOrderDirection(ordering).ifPresent(coreErrors::add);
        validatorMandatoryOrderBy(ordering).ifPresent(coreErrors::add);
        validatorMandatoryOrderDirection(ordering).ifPresent(coreErrors::add);
        return coreErrors;
    }

    private Optional<CoreError> validatorOrderBy(OrderingTable ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("title")))
                ? Optional.of(new CoreError("orderBy", "might contain title table only!"))
                : Optional.empty();
    }
    private Optional<CoreError> validatorOrderDirection(OrderingTable ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING")
                || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "might contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderBy(OrderingTable ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "can not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderDirection(OrderingTable ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "can not be empty!"))
                : Optional.empty();
    }
}
