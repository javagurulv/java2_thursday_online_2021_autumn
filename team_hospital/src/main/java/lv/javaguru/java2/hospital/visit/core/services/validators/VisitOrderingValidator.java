package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class VisitOrderingValidator {

	public List<CoreError> validate(SearchVisitRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if(request != null) {
			validateOrderBy(request).ifPresent(errors::add);
			validateOrderDirection(request).ifPresent(errors::add);
			//validateMandatoryOrderBy(request).ifPresent(errors::add);
			//validateMandatoryOrderDirection(request).ifPresent(errors::add);
		}
		return errors;
	}

	private Optional<CoreError> validateOrderBy(SearchVisitRequest request) {
		return request.getOrderBy() != null && !request.getOrderBy().isEmpty()
				&& !(request.getOrderBy().toUpperCase(Locale.ROOT).equals("DATE")
				|| request.getOrderBy().toUpperCase(Locale.ROOT).equals("ID"))
				? Optional.of(new CoreError("Order by", "must contain 'ID' or 'DATE' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(SearchVisitRequest request) {
		return (request.getOrderDirection() != null && !request.getOrderDirection().isEmpty()
				&& !(request.getOrderDirection().toUpperCase(Locale.ROOT).equals("ASCENDING") ||
				request.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")))
				? Optional.of(new CoreError("Order direction", "must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(SearchVisitRequest request) {
		return (request.getOrderDirection() != null && request.getOrderBy() == null)
				? Optional.of(new CoreError("OrderBy", "must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(SearchVisitRequest request) {
		return (request.getOrderBy() != null && request.getOrderDirection() == null)
				? Optional.of(new CoreError("OrderDirection", "must not be empty!"))
				: Optional.empty();
	}

}
