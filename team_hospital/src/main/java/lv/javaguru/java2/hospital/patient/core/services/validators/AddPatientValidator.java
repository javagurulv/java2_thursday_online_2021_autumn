package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddPatientValidator {

    @Autowired private PatientDatabaseImpl database;

    public List<CoreError> validate(AddPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validatePatientExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddPatientRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validateSurname(AddPatientRequest request) {
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validatePersonalCode(AddPatientRequest request) {
        return (request.getPersonalCode() == null || request.getPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Personal code", "Must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validatePatientExistence(AddPatientRequest request) {
        return request == null
                ? Optional.empty() : database.findPatientByNameSurnamePersonalCode(
                    request.getName(),
                    request.getSurname(),
                    request.getPersonalCode()).isEmpty()
                ? Optional.empty()
                : Optional.of(new CoreError("Patient", "already exist!"));
    }
}
