package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditDoctorRequestValidator {

    public List<CoreError> validate(EditDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
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
        return (request.getEditOption() == null
                && !request.getEditOption().equals("NAME")
                || request.getEditOption().equals("SURNAME")
                || request.getEditOption().equals("SPECIALITY"))
                ? Optional.of(new CoreError("edit option", "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!"))
                : Optional.empty();
    }


    private Optional<CoreError> validateChanges(EditDoctorRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }
}
