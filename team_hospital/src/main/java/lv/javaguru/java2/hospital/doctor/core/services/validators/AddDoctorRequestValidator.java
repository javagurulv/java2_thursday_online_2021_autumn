package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceForAddValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddDoctorRequestValidator {

    @Autowired
    private DoctorExistenceForAddValidator doctorExistenceForAddValidator;

    public List<CoreError> validate(AddDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateSpeciality(request).ifPresent(errors::add);
        validateExistence(request, errors);
        return errors;
    }

    private Optional<CoreError> validateName(AddDoctorRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddDoctorRequest request) {
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("surname", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSpeciality(AddDoctorRequest request) {
        return (request.getSpeciality() == null || request.getSpeciality().isEmpty())
                ? Optional.of(new CoreError("speciality", "Must not be empty!"))
                : Optional.empty();
    }

    private void validateExistence(AddDoctorRequest request, List<CoreError> errors) {
        Optional<CoreError> error = doctorExistenceForAddValidator.validateDoctorExistence(request);
        error.ifPresent(errors::add);
    }

}
