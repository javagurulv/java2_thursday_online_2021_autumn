package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderingValidator {

    public List<CoreError> validator(Ordering ordering) {
        List<CoreError> errors = new ArrayList<>();
            validatorOrderBy(ordering).ifPresent(errors::add);
            validatorOrderDirection(ordering).ifPresent(errors::add);
            validatorMandatoryOrderBy(ordering).ifPresent(errors::add);
            validatorMandatoryOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatorOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("name")
                || ordering.getOrderBy().equals("surname")))
                ? Optional.of(new CoreError("orderBy", "might contain 'name' or 'surname' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING")
                || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "might contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "can not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "can not be empty!"))
                : Optional.empty();
    }
}
