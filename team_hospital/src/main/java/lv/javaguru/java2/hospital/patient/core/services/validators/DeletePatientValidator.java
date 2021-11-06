package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeletePatientValidator {

    @Autowired private PatientLongNumChecker longNumChecker;
    @Autowired private PatientExistenceByIDValidator validator;

    public List<CoreError> validate(DeletePatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        validateNumInID(request).ifPresent(errors::add);
        validatePatientExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(DeletePatientRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateNumInID(DeletePatientRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getIdRequest(), "ID");
    }

    private Optional<CoreError> validatePatientExistence(DeletePatientRequest request) {
        if (request.getIdRequest() == null) {
            return Optional.empty();
        }
        return validator.existenceByID(request.getIdRequest());
    }
}

