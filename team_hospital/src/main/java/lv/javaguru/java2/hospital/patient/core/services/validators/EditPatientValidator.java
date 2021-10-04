package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class EditPatientValidator {

    public List<CoreError> validate(EditPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(request).ifPresent(errors::add);
        validateUserChoice(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePatientID(EditPatientRequest request) {
        return (request.getPatientID() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateUserChoice(EditPatientRequest request) {
        return (request.getUserInput() == null)
                ? Optional.of(new CoreError("User choice", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditPatientRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("Changes", "Must not be empty!")) : Optional.empty();
    }
}
