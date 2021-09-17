package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.Ordering;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorsRequestValidator {

	private OrderingValidator orderingValidator;
	private PagingValidator pagingValidator;

	public SearchDoctorsRequestValidator(OrderingValidator orderingValidator,
										 PagingValidator pagingValidator) {
		this.orderingValidator = orderingValidator;
		this.pagingValidator = pagingValidator;
	}

	public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = validateSearchFields(request);
		validateOrderingIfNeeded(request, errors);
		validatePagingIfNeeded(request, errors);
		return errors;
    }

	private void validatePagingIfNeeded(SearchDoctorsRequest request, List<CoreError> errors) {
		if(request.getPaging() != null) {
        	List<CoreError> pagingErrors = pagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
	}

	private void validateOrderingIfNeeded(SearchDoctorsRequest request, List<CoreError> errors) {
		if (request.getOrdering() != null) {
			List<CoreError> orderingErrors = orderingValidator.validate(request.getOrdering());
			errors.addAll(orderingErrors);
		}
	}

	private List<CoreError> validateSearchFields(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getId()) && isEmpty(request.getName()) && isEmpty(request.getSurname()) && isEmpty(request.getSpeciality())) {
            errors.add(new CoreError("id", "Must not be empty!"));
            errors.add(new CoreError("name", "Must not be empty!"));
            errors.add(new CoreError("surname", "Must not be empty!"));
            errors.add(new CoreError("speciality", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
