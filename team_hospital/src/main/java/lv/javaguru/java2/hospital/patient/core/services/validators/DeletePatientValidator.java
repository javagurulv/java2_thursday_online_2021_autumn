package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeletePatientValidator {

    public List<CoreError> validate(DeletePatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(DeletePatientRequest request) {
        return (request.getIdRequest() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }
}

