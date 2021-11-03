package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VisitOrderingValidator {

	public List<CoreError> validate(VisitOrdering visitOrdering) {
		List<CoreError> errors = new ArrayList<>();
		validateOrderBy(visitOrdering).ifPresent(errors::add);
		validateOrderDirection(visitOrdering).ifPresent(errors::add);
		validateMandatoryOrderBy(visitOrdering).ifPresent(errors::add);
		validateMandatoryOrderDirection(visitOrdering).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateOrderBy(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderBy() != null)
				&& !(visitOrdering.getOrderBy().equals("date") || visitOrdering.getOrderBy().equals("id"))
				? Optional.of(new CoreError("OrderBy", "must contain 'id' or 'date' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderDirection() != null
				&& !(visitOrdering.getOrderDirection().equals("ASCENDING") || visitOrdering.getOrderDirection().equals("DESCENDING")))
				? Optional.of(new CoreError("OrderDirection", "must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderDirection() != null && visitOrdering.getOrderBy() == null)
				? Optional.of(new CoreError("OrderBy", "must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderBy() != null && visitOrdering.getOrderDirection() == null)
				? Optional.of(new CoreError("OrderDirection", "must not be empty!"))
				: Optional.empty();
	}

}
