package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class PatientOrderingValidator {

    public List<CoreError> validate(SearchPatientsRequest request){
        List<CoreError> errors = new ArrayList<>();
        if(request != null) {
            validateOrderBy(request).ifPresent(errors::add);
            validateOrderDirection(request).ifPresent(errors::add);
            //validateMandatoryOrderBy(request).ifPresent(errors::add);
            //validateMandatoryOrderDirection(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateOrderBy(SearchPatientsRequest request){
        return request.getOrderBy() != null && !request.getOrderBy().isEmpty()
                && !(request.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                || request.getOrderBy().toUpperCase(Locale.ROOT).equals("SURNAME"))
                ? Optional.of(new CoreError("orderBy", "must contain 'NAME' or 'SURNAME' only!"))
                : Optional.empty();

    }

    private Optional<CoreError> validateOrderDirection(SearchPatientsRequest request) {
        return (request.getOrderDirection() != null && !request.getOrderDirection().isEmpty()
                && !(request.getOrderDirection().toUpperCase(Locale.ROOT).equals("ASCENDING")
                || request.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")))
                ? Optional.of(new CoreError("Order Direction", "must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(SearchPatientsRequest request) {
        return (request.getOrderDirection() != null && request.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(SearchPatientsRequest request) {
        return (request.getOrderBy() != null && request.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "must not be empty!"))
                : Optional.empty();
    }
}
