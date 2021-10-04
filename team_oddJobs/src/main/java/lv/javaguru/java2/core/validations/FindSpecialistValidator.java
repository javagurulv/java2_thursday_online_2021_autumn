package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class FindSpecialistValidator {

    @DIDependency private FindSpecialistsFieldValidator fieldValidator;
    @DIDependency private SpecialistOrderingValidator specialistOrderingValidator;
    @DIDependency private SpecialistPagingValidator specialistPagingValidator;


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
