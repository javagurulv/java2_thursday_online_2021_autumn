package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditPrescriptionValidator {

    @Autowired private PatientDatabase patientDatabase;
    @Autowired private PrescriptionExistenceByIDValidator prescriptionExistenceByIDValidator;
    @Autowired private PrescriptionEnumChecker enumChecker;
    @Autowired private IntegerNumChecker integerNumChecker;
    @Autowired private LongNumChecker longNumChecker;

    public List<CoreError> validate(EditPrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePrescriptionID(request).ifPresent(errors::add);
        validateRequestID(request).ifPresent(errors::add);
        validateRequestUserChoice(request).ifPresent(errors::add);
        validateRequestChanges(request).ifPresent(errors::add);
        validateUserChoiceEnum(request).ifPresent(errors::add);
        validateIDInChanges(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePrescriptionExistence(request).ifPresent(errors::add);
            validatePatientExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateRequestID(EditPrescriptionRequest request) {
        return request.getPrescriptionID() == null ?
                Optional.of(new CoreError("Prescription ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateRequestUserChoice(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty() ?
                Optional.of(new CoreError("User choice", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateRequestChanges(EditPrescriptionRequest request) {
        return request.getChanges() == null || request.getChanges().isEmpty()
                ? Optional.of(new CoreError("Changes", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePrescriptionID(EditPrescriptionRequest request) {
        return request.getPrescriptionID() == null || request.getPrescriptionID().isEmpty()
                ? Optional.empty() : longNumChecker.validate(request.getPrescriptionID());
    }

    private Optional<CoreError> validateUserChoiceEnum(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty()
                ? Optional.empty() : enumChecker.validate(request.getEditPrescriptionEnum());
    }

    private Optional<CoreError> validateIDInChanges(EditPrescriptionRequest request) {
        return request.getEditPrescriptionEnum() == null || request.getEditPrescriptionEnum().isEmpty()
                ? Optional.empty() : !request.getEditPrescriptionEnum().equals("PATIENT")
                ? Optional.empty() : integerNumChecker.validate(request.getChanges());
    }

    private Optional<CoreError> validatePrescriptionExistence(EditPrescriptionRequest request) {
        return request.getPrescriptionID() == null || request.getPrescriptionID().isEmpty() ?
                Optional.empty() : prescriptionExistenceByIDValidator.execute(Long.valueOf(request.getPrescriptionID()));
    }

    private Optional<CoreError> validatePatientExistence(EditPrescriptionRequest request){
        return !request.getEditPrescriptionEnum().equals("PATIENT")
                ? Optional.empty() : patientDatabase.findById(Long.valueOf(request.getChanges())).isPresent()
                ? Optional.empty() : Optional.of(new CoreError("Patient", "does not exist!"));
    }
}
