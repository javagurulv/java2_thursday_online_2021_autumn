package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceForSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchDoctorsRequestValidator {

	@Autowired private SearchDoctorsRequestFieldValidator fieldValidator;
	@Autowired private DoctorOrderingValidator doctorOrderingValidator;
	@Autowired private DoctorPagingValidator doctorPagingValidator;
	@Autowired private DoctorExistenceForSearchValidator doctorExistenceForSearchValidator;

	public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
		validateOrderingIfPresent(request, errors);
		validatePagingIfPresent(request, errors);
		validateDoctorExistence(request, errors);
		return errors;
    }

	private void validatePagingIfPresent(SearchDoctorsRequest request, List<CoreError> errors) {
		if(request.getPaging() != null) {
        	List<CoreError> pagingErrors = doctorPagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
	}

	private void validateOrderingIfPresent(SearchDoctorsRequest request, List<CoreError> errors) {
		if (request.getOrdering() != null) {
			List<CoreError> orderingErrors = doctorOrderingValidator.validate(request.getOrdering());
			errors.addAll(orderingErrors);
		}
	}

	private void validateDoctorExistence(SearchDoctorsRequest request, List<CoreError> errors) {
		List<CoreError> existenceErrors = doctorExistenceForSearchValidator.validate(request);
		errors.addAll(existenceErrors);
	}

}
