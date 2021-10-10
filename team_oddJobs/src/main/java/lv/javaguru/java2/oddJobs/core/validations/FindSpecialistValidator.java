package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindSpecialistValidator {

    @Autowired
    private FindSpecialistsFieldValidator fieldValidator;
    @Autowired private SpecialistOrderingValidator specialistOrderingValidator;
    @Autowired private SpecialistPagingValidator specialistPagingValidator;


    public List<CoreError> validate(FindSpecialistRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validatePagingIfPresent(FindSpecialistRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            Paging paging = request.getPaging();
            errors.addAll(specialistPagingValidator.validate(paging));
        }
    }

    private void validateOrderingIfPresent(FindSpecialistRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            errors.addAll(specialistOrderingValidator.validate(ordering));
        }
    }
}
