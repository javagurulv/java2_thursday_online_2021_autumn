package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeletePatientValidator {

    private final PatientDatabaseImpl database;

    public DeletePatientValidator(PatientDatabaseImpl database) {
        this.database = database;
    }

    public List<CoreError> validate(DeletePatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        //validatePatientExists(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(DeletePatientRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExists(DeletePatientRequest request) {
        if (request.getIdRequest() == null || request.getIdRequest().isEmpty()) {
            return Optional.empty();
        } else {
            return !database.patientExists(Long.parseLong(request.getIdRequest())) ?
                    Optional.of(new CoreError("Patient", "does not exist."))
                    : Optional.empty();
        }
    }
}

