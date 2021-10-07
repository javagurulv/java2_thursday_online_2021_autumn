package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.List;

@DIComponent
public class SearchDoctorsRequestValidator {

	@DIDependency private SearchDoctorsRequestFieldValidator fieldValidator;
	@DIDependency private OrderingValidator orderingValidator;
	@DIDependency private PagingValidator pagingValidator;

	public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
		validateOrderingIfPresent(request, errors);
		validatePagingIfPresent(request, errors);
		return errors;
    }

	private void validatePagingIfPresent(SearchDoctorsRequest request, List<CoreError> errors) {
		if(request.getPaging() != null) {
        	List<CoreError> pagingErrors = pagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
	}

	private void validateOrderingIfPresent(SearchDoctorsRequest request, List<CoreError> errors) {
		if (request.getOrdering() != null) {
			List<CoreError> orderingErrors = orderingValidator.validate(request.getOrdering());
			errors.addAll(orderingErrors);
		}
	}

}
