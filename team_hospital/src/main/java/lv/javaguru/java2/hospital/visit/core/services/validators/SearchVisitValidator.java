package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateFormatValidator;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceForSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchVisitValidator {

    @Autowired private SearchVisitFieldValidator fieldValidator;
    @Autowired private VisitOrderingValidator visitOrderingValidator;
    @Autowired private VisitPagingValidator visitPagingValidator;
    @Autowired private VisitExistenceForSearchValidator visitExistenceForSearchValidator;
    @Autowired private DateFormatValidator dateValidator;

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateDate(request).ifPresent(errors::add);
        if(errors.isEmpty()) {
            validateOrderingIfPresent(request, errors);
            validatePagingIfPresent(request, errors);
            validateVisitExistence(request, errors);
        }
        return errors;
    }

    private Optional<CoreError> validateDate(SearchVisitRequest request){
        return request.getVisitDate() == null || request.getVisitDate().isEmpty()
                ? Optional.empty() : dateValidator.validateFormat(request.getVisitDate());
    }

    private void validatePagingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (!request.isVisitIdProvided() || !request.isDoctorIdProvided()
                || !request.isPatientIdProvided() || !request.isDateProvided()) {
            List<CoreError> pagingErrors = visitPagingValidator.validate(request);
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (!request.isVisitIdProvided() || !request.isDoctorIdProvided()
                || !request.isPatientIdProvided() || !request.isDateProvided()) {
            List<CoreError> orderingErrors = visitOrderingValidator.validate(request);
            errors.addAll(orderingErrors);
        }
    }

    private void validateVisitExistence(SearchVisitRequest request, List<CoreError> errors) {
        List<CoreError> existenceErrors = visitExistenceForSearchValidator.validate(request);
        errors.addAll(existenceErrors);
    }
}
