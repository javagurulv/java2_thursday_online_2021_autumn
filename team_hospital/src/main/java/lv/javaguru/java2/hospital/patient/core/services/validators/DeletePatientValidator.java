package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;

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
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.empty() : database.patientExists(Long.valueOf(request.getIdRequest()))
                ? Optional.empty() : Optional.of(new CoreError("Patient", "does not exist."));
    }
}

