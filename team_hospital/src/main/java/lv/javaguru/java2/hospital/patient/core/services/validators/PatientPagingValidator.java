package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PatientPagingValidator {

    public List<CoreError> validate(PatientPaging patientPaging){
        List<CoreError> errors = new ArrayList<>();
        if(patientPaging != null) {
            validatePageNumber(patientPaging).ifPresent(errors::add);
            validatePageSize(patientPaging).ifPresent(errors::add);
            validateMandatoryPageSize(patientPaging).ifPresent(errors::add);
            validateMandatoryPageNumber(patientPaging).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePageNumber(PatientPaging patientPaging) {
        return (patientPaging.getPageNumber() != null
                && patientPaging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(PatientPaging patientPaging) {
        return (patientPaging.getPageSize() != null
                && patientPaging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(PatientPaging patientPaging) {
        return (patientPaging.getPageNumber() == null && patientPaging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(PatientPaging patientPaging) {
        return (patientPaging.getPageSize() == null && patientPaging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "must not be empty!"))
                : Optional.empty();
    }
}
