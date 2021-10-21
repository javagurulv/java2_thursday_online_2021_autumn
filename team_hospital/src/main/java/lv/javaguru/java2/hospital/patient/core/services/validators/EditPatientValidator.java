package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditPatientValidator {

    @Autowired PatientExistenceByIDValidator validator;
    @Autowired PatientEnumChecker checker;

    public List<CoreError> validate(EditPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(request).ifPresent(errors::add);
        validateUserChoice(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validatePatientExistence(request).ifPresent(errors::add);
        validateEnum(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePatientID(EditPatientRequest request) {
        return (request.getPatientID() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateUserChoice(EditPatientRequest request) {
        return (request.getUserInputEnum() == null || request.getUserInputEnum().isEmpty())
                ? Optional.of(new CoreError("User choice", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditPatientRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("Changes", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(EditPatientRequest request) {
        return request.getPatientID() == null ? Optional.empty() : validator.existenceByID(request.getPatientID());
    }

    private Optional<CoreError> validateEnum(EditPatientRequest request){
        return  (request.getUserInputEnum() == null || request.getUserInputEnum().isEmpty())
                ? Optional.empty() : checker.validateEnum(request.getUserInputEnum());
    }
}
