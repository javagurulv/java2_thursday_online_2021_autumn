package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditPatientValidator {

    private final PatientDatabaseImpl database;

    public EditPatientValidator(PatientDatabaseImpl database) {
        this.database = database;
    }

    public List<CoreError> validate(EditPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(request).ifPresent(errors::add);
        validateUserChoice(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePatientID(EditPatientRequest request) {
        return (request.getPatientID() == null || request.getPatientID().isEmpty())
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateUserChoice(EditPatientRequest request) {
        return (request.getUserInput() == null || request.getUserInput().isEmpty())
                ? Optional.of(new CoreError("User choice", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditPatientRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("Changes", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExists(EditPatientRequest request) {
            return (request.getPatientID() == null || request.getPatientID().isEmpty())
                    ? Optional.empty() : database.patientExists(Long.valueOf(request.getPatientID()))
                    ? Optional.empty() : Optional.of(new CoreError("Patient", "does not exist."));
        }
}
