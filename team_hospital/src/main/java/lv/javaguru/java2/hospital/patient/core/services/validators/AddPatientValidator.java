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

    @Autowired
    private PatientDatabaseImpl database;

    public List<CoreError> validate(AddPatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validatePatientExistence(request).ifPresent(errors::add);
        validatePersonalCodeLength(request).ifPresent(errors::add);
        validateNumInPersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddPatientRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validateSurname(AddPatientRequest request) {
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validatePersonalCode(AddPatientRequest request) {
        return (request.getPersonalCode() == null || request.getPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Personal code", "must not be empty!")) : Optional.empty();

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

    private Optional<CoreError> validatePersonalCodeLength(AddPatientRequest request) {
        return request.getPersonalCode().length() == 11 || request.getPersonalCode().isEmpty()
                ? Optional.empty()
                : Optional.of(new CoreError("Personal code", "must consist of 11 numbers!"));
    }

    private Optional<CoreError> validateNumInPersonalCode(AddPatientRequest request) {
        if (request.getPersonalCode() == null || request.getPersonalCode().isEmpty()) {
            return Optional.empty();
        } else {
            for (String s : request.getPersonalCode().split("")) {
                try {
                    Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return Optional.of(new CoreError("Personal code", "must consist only from numbers!"));
                }
            }
        }
        return Optional.empty();
    }
}
