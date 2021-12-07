package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.checkers.DoctorLongNumChecker;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditDoctorRequestValidator {

    @Autowired
    private DoctorExistenceByIdValidator validator;
    @Autowired private DoctorEnumChecker checker;
    @Autowired private DoctorLongNumChecker numChecker;

    public List<CoreError> validate(EditDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateEditOption(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validateIDForNum(request).ifPresent(errors::add);
        if(errors.isEmpty()) {
            validateDoctorExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateId(EditDoctorRequest request) {
        return (request.getDoctorId() == null || request.getDoctorId().isEmpty())
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEditOption(EditDoctorRequest request) {
        return (request.getUserInputEnum() == null || request.getUserInputEnum().isEmpty())
                ? Optional.of(new CoreError("User choice", "Must not be empty!"))
                : checker.validateEnum(request.getUserInputEnum());
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

    private Optional<CoreError> validateIDForNum(EditDoctorRequest request){
        return request.getDoctorId() == null || request.getDoctorId().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getDoctorId(), "ID");
    }
}
