package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.checkers.PrescriptionLongNumChecker;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeletePrescriptionValidator {

    @Autowired
    private PrescriptionExistenceByIDValidator validator;
    @Autowired private PrescriptionLongNumChecker longNumChecker;

    public List<CoreError> validate(DeletePrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePrescriptionExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeletePrescriptionRequest request) {
        return (request.getPrescriptionIdToDelete() == null || request.getPrescriptionIdToDelete().isEmpty())
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrescriptionExistence(DeletePrescriptionRequest request) {
        if(request.getPrescriptionIdToDelete() == null || request.getPrescriptionIdToDelete().isEmpty()) {
            return Optional.empty();
        }
        return validator.execute(Long.parseLong(request.getPrescriptionIdToDelete()));
    }

    private Optional<CoreError> validateNumInID(DeletePrescriptionRequest request) {
        return (request.getPrescriptionIdToDelete() == null || request.getPrescriptionIdToDelete().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getPrescriptionIdToDelete(), "ID");
    }

}
