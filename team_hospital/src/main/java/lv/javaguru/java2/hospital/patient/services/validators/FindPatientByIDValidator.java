package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindPatientByIDValidator {

    private final PatientDatabaseImpl database;

    public FindPatientByIDValidator(PatientDatabaseImpl database) {
        this.database = database;
    }

    public List<CoreError> validate(FindPatientByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        //validatePatientExists(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(FindPatientByIdRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExists(FindPatientByIdRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.empty() : database.patientExists(Long.valueOf(request.getIdRequest()))
                ? Optional.empty() : Optional.of(new CoreError("Patient", "does not exist."));
    }
}