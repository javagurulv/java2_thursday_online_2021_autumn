package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowDoctorVisitsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.ExistenceByDoctorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShowDoctorVisitsRequestValidator {

    @Autowired private DoctorExistenceByIdValidator validator;

    public List<CoreError> validate(ShowDoctorVisitsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateDoctorExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(ShowDoctorVisitsRequest request) {
        return (request.getDoctorId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(ShowDoctorVisitsRequest request) {
        if (request.getDoctorId() == null) {
            return Optional.empty();
        }
        return validator.validateExistenceById(request.getDoctorId());
    }

}
