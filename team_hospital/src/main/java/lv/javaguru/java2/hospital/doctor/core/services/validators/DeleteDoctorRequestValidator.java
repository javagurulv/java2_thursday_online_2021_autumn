package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteDoctorRequestValidator {

    @Autowired private DoctorExistenceByIdValidator validator;

    public List<CoreError> validate(DeleteDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateDoctorExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteDoctorRequest request) {
        return (request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty())
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(DeleteDoctorRequest request) {
        if (request.getDoctorIdToDelete() == null) {
            return Optional.empty();
        }
        return validator.validateExistenceById(request.getDoctorIdToDelete());
    }

}
