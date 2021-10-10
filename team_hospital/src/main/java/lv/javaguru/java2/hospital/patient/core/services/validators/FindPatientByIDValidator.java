package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FindPatientByIDValidator {

    public List<CoreError> validate(FindPatientByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(FindPatientByIdRequest request) {
        return (request.getIdRequest() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }
}