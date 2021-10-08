package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class PatientOrderingValidator {

    public List<CoreError> validate(PatientOrdering patientOrdering){
        List<CoreError> errors = new ArrayList<>();
        if(patientOrdering != null) {
            validateOrderBy(patientOrdering).ifPresent(errors::add);
            validateOrderDirection(patientOrdering).ifPresent(errors::add);
            validateMandatoryOrderBy(patientOrdering).ifPresent(errors::add);
            validateMandatoryOrderDirection(patientOrdering).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateOrderBy(PatientOrdering patientOrdering){
        return (patientOrdering.getOrderBy() != null
                && !(patientOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                || patientOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("SURNAME")))
                ? Optional.of(new CoreError("orderBy", "must contain 'NAME' or 'SURNAME' only!"))
                : Optional.empty();

    }

    private Optional<CoreError> validateOrderDirection(PatientOrdering patientOrdering) {
        return (patientOrdering.getOrderDirection() != null
                && !(patientOrdering.getOrderDirection().toUpperCase(Locale.ROOT).equals("ASCENDING")
                || patientOrdering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")))
                ? Optional.of(new CoreError("Order Direction", "must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(PatientOrdering patientOrdering) {
        return (patientOrdering.getOrderDirection() != null && patientOrdering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(PatientOrdering patientOrdering) {
        return (patientOrdering.getOrderBy() != null && patientOrdering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "must not be empty!"))
                : Optional.empty();
    }
}
