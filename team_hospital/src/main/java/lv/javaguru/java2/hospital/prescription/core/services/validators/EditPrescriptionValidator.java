package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditPrescriptionValidator {

    @Autowired private PrescriptionExistenceByIDValidator idValidator;
    @Autowired private PrescriptionEnumChecker enumChecker;
    @Autowired private IDNumChecker idNumChecker;

    public List<CoreError> validate(EditPrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePrescriptionExistence(request).ifPresent(errors::add);
        validateRequestID(request).ifPresent(errors::add);
        validateRequestUserChoice(request).ifPresent(errors::add);
        validateRequestChanges(request).ifPresent(errors::add);
        validateUserChoiceEnum(request).ifPresent(errors::add);
        validateIDInChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateRequestID(EditPrescriptionRequest request) {
        return request.getPrescriptionID() == null ?
                Optional.of(new CoreError("ID field", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateRequestUserChoice(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty() ?
                Optional.of(new CoreError("User choice", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateRequestChanges(EditPrescriptionRequest request) {
        return request.getChanges() == null || request.getChanges().isEmpty() ?
                Optional.of(new CoreError("Changes", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePrescriptionExistence(EditPrescriptionRequest request) {
        return request.getPrescriptionID() == null ?
                Optional.empty() : idValidator.execute(request.getPrescriptionID());
    }

    private Optional<CoreError> validateUserChoiceEnum(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty() ?
                Optional.empty() : enumChecker.validate(request.getEditPrescriptionEnum());
    }

    private Optional<CoreError> validateIDInChanges(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty() ?
                Optional.empty() : !request.getEditPrescriptionEnum().equals("PATIENT") ?
                Optional.empty() : idNumChecker.validate(request.getChanges());
    }
}
