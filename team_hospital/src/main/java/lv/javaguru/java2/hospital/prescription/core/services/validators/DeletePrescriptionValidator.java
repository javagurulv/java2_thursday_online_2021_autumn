package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeletePrescriptionValidator {

    @Autowired
    private PrescriptionExistenceByIDValidator validator;

    public List<CoreError> validate(DeletePrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePrescriptionExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeletePrescriptionRequest request) {
        return (request.getPrescriptionIdToDelete() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrescriptionExistence(DeletePrescriptionRequest request) {
        if(request.getPrescriptionIdToDelete() == null ) {
            return Optional.empty();
        }
        return validator.execute(request.getPrescriptionIdToDelete());
    }
}
