package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionOrdering;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PrescriptionOrderingValidator {

    public List<CoreError> validate(PrescriptionOrdering ordering) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(ordering).ifPresent(errors::add);
        validateOrderDirection(ordering).ifPresent(errors::add);
        validateMandatoryOrderBy(ordering).ifPresent(errors::add);
        validateMandatoryOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateOrderBy(PrescriptionOrdering ordering) {
        return (ordering.getOrderBy() != null)
                && !(ordering.getOrderBy().equals("date"))
                ? Optional.of(new CoreError("orderBy", "Must contain 'date' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(PrescriptionOrdering ordering) {
        return (ordering.getOrderDirection() != null)
                && !(ordering.getOrderDirection().equals("ASCENDING")) || ordering.getOrderDirection().equals("DESCENDING")
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(PrescriptionOrdering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(PrescriptionOrdering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }
}
