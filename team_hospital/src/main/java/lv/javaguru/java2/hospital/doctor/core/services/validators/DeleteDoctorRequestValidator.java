package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class DeleteDoctorRequestValidator {

    public List<CoreError> validate(DeleteDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteDoctorRequest request) {
        return (request.getDoctorIdToDelete() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}
