package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditDoctorRequestValidator {

    @Autowired
    private DoctorExistenceByIdValidator validator;

    public List<CoreError> validate(EditDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateDoctorExistence(request).ifPresent(errors::add);
        validateEditOption(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(EditDoctorRequest request) {
        return (request.getDoctorId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEditOption(EditDoctorRequest request) {
        if (request.getUserInputEnum() == null || request.getUserInputEnum().isEmpty()) {
            return Optional.of(new CoreError("edit option", "Must not be empty!"));
        }
        else if (!request.getUserInputEnum().equals("NAME")
                    && !request.getUserInputEnum().equals("SURNAME")
                    && !request.getUserInputEnum().equals("SPECIALITY")) {
                return Optional.of(new CoreError("edit option", "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!"));
            }
        return Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditDoctorRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(EditDoctorRequest request) {
        if (request.getDoctorId() == null) {
            return Optional.empty();
        }
        return validator.validateExistenceById(request.getDoctorId());
    }
}
