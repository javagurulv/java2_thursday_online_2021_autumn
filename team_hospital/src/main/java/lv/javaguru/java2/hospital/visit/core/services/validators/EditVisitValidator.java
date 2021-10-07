package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditVisitValidator {

    public List<CoreError> validate(EditVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(EditVisitRequest request) {
        return (request.getVisitID() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditVisitRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }
}
