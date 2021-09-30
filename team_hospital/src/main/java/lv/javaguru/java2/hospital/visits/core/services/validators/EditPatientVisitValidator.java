package lv.javaguru.java2.hospital.visits.core.services.validators;

import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import lv.javaguru.java2.hospital.visits.core.request.EditPatientVisitRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditPatientVisitValidator {

    public List<CoreError> validate(EditPatientVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(EditPatientVisitRequest request) {
        return (request.getVisitID() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditPatientVisitRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }
}
