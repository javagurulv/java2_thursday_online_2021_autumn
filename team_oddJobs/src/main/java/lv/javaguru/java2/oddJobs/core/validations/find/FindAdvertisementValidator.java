package lv.javaguru.java2.oddJobs.core.validations.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.AdvertisementOrderingValidator;
import lv.javaguru.java2.oddJobs.core.validations.PagingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAdvertisementValidator {

    @Autowired
    private FindAdvertisementFieldValidator fieldValidator;
    @Autowired
    private AdvertisementOrderingValidator orderingValidator;
    @Autowired
    private PagingValidator pagingValidator;


    public List<CoreError> validate(FindAdvertisementRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validatePagingIfPresent(FindAdvertisementRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            Paging paging = request.getPaging();
            errors.addAll(pagingValidator.validate(paging));
        }
    }

    private void validateOrderingIfPresent(FindAdvertisementRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            errors.addAll(orderingValidator.validate(ordering));
        }
    }
}
