package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.Ordering;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class OrderingValidator {
    public List<CoreError> validate(Ordering ordering){
        List<CoreError> errors = new ArrayList<>();
        if(ordering != null) {
            validateOrderBy(ordering).ifPresent(errors::add);
            validateOrderDirection(ordering).ifPresent(errors::add);
            validateMandatoryOrderBy(ordering).ifPresent(errors::add);
            validateMandatoryOrderDirection(ordering).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering){
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                || ordering.getOrderBy().toUpperCase(Locale.ROOT).equals("SURNAME")))
                ? Optional.of(new CoreError("orderBy", "must contain 'NAME' or 'SURNAME' only!"))
                : Optional.empty();

    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().toUpperCase(Locale.ROOT).equals("ASCENDING")
                || ordering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")))
                ? Optional.of(new CoreError("Order Direction", "must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "must not be empty!"))
                : Optional.empty();
    }
}
