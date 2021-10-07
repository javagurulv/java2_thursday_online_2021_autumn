package lv.javaguru.java2.hospital.patient.core.services.validators;
import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import java.util.List;

@DIComponent
public class SearchPatientsValidator {

    @DIDependency private SearchPatientsRequestFieldValidator fieldValidator;
    @DIDependency private OrderingValidator orderingValidator;
    @DIDependency private PagingValidator pagingValidator;

    public List<CoreError> validate(SearchPatientsRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validatePagingIfNeeded(request, errors);
        validateOrderingIfNeeded(request, errors);
        return errors;
    }

    private void validatePagingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            List<CoreError> pagingErrors = pagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            List<CoreError> orderingErrors = orderingValidator.validate(request.getOrdering());
            errors.addAll(orderingErrors);
        }
    }
}
