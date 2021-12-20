package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PersonalCodeChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class EditPatientValidator {

    @Autowired
    private PatientExistenceByIDValidator validator;
    @Autowired
    private PatientEnumChecker checker;
    @Autowired
    private PersonalCodeChecker personalCodeChecker;
    @Autowired
    private PatientLongNumChecker numChecker;

    public List<CoreError> validate(EditPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(request).ifPresent(errors::add);
        validateUserChoice(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validateEnum(request).ifPresent(errors::add);
        validateNumInPersonalCode(request).ifPresent(errors::add);
        validatePersonalCodeLength(request).ifPresent(errors::add);
        validateIDForNum(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePatientExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePatientID(EditPatientRequest request) {
        return (request.getPatientID() == null || request.getPatientID().isEmpty())
                ? Optional.of(new CoreError("ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateUserChoice(EditPatientRequest request) {
        return (request.getFieldToChange() == null || request.getFieldToChange().isEmpty())
                ? Optional.of(new CoreError("User choice", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditPatientRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("Changes", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(EditPatientRequest request) {
        return request.getPatientID() == null || request.getPatientID().isEmpty()
                ? Optional.empty() : validator.existenceByID(request.getPatientID());
    }

    private Optional<CoreError> validateEnum(EditPatientRequest request) {
        return (request.getFieldToChange() == null || request.getFieldToChange().isEmpty())
                ? Optional.empty() : checker.validateEnum(request.getFieldToChange());
    }

    private Optional<CoreError> validateNumInPersonalCode(EditPatientRequest request) {
        return request.getFieldToChange() == null || request.getFieldToChange().isEmpty()
                ? Optional.empty() : !request.getFieldToChange().toUpperCase(Locale.ROOT).equals("PERSONAL_CODE")
                ? Optional.empty() : personalCodeChecker.execute(request.getChanges());
    }

    private Optional<CoreError> validatePersonalCodeLength(EditPatientRequest request) {
        return request.getFieldToChange() == null || request.getFieldToChange().isEmpty()
                ? Optional.empty() : !request.getFieldToChange().toUpperCase(Locale.ROOT).equals("PERSONAL_CODE")
                ? Optional.empty() : request.getChanges().length() == 11
                ? Optional.empty() : Optional.of(new CoreError("Personal code", "must consist of 11 numbers!"));
    }

    private Optional<CoreError> validateIDForNum(EditPatientRequest request){
        return request.getPatientID() == null || request.getPatientID().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getPatientID(), "ID");
    }
}
