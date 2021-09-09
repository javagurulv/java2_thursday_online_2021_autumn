package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddPatientValidator {
    public List<CoreError> validate (AddPatientRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddPatientRequest request){
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validateSurname(AddPatientRequest request){
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!")) : Optional.empty();

    }

    private Optional<CoreError> validatePersonalCode(AddPatientRequest request){
        return (request.getPersonalCode() == null || request.getPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Personal code", "Must not be empty!")) : Optional.empty();

    }
}
