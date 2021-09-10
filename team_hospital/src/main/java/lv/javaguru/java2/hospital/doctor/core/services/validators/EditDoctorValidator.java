package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditDoctorValidator {

    public List<CoreError> validate(EditDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(EditDoctorRequest request) {
        return (request.getDoctorId() == 0)
                ? Optional.of(new CoreError("id", "Must not be 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateChanges(EditDoctorRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }
}
