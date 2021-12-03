package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FindPatientByIDValidator {

    @Autowired private PatientLongNumChecker longNumChecker;
    @Autowired private PatientExistenceByIDValidator idValidator;

    public List<CoreError> validate(FindPatientByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        validateNumInID(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePatientExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateID(FindPatientByIdRequest request) {
        return (request.getIDRequest() == null || request.getIDRequest().isEmpty())
                ? Optional.of(new CoreError("ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateNumInID(FindPatientByIdRequest request) {
        return (request.getIDRequest() == null || request.getIDRequest().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getIDRequest(), "ID");
    }

    private Optional<CoreError> validatePatientExistence(FindPatientByIdRequest request) {
        return request.getIDRequest() == null || request.getIDRequest().isEmpty() ?
             Optional.empty() : idValidator.existenceByID(request.getIDRequest());
    }
}