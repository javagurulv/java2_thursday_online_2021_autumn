package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.FindDoctorByIdRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindDoctorByIdValidator {

    public List<CoreError> validate(FindDoctorByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validateId(FindDoctorByIdRequest request) {
        return (request.getDoctorId() == 0)
                ? Optional.of(new CoreError("id", "Must not be 0!"))
                : Optional.empty();
    }
}
