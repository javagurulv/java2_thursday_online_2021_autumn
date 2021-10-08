package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				&& !(visitOrdering.getOrderBy().equals("date"))
				? Optional.of(new CoreError("orderBy", "Must contain 'date' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderDirection() != null
				&& !(visitOrdering.getOrderDirection().equals("ASCENDING") || visitOrdering.getOrderDirection().equals("DESCENDING")))
				? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderDirection() != null && visitOrdering.getOrderBy() == null)
				? Optional.of(new CoreError("orderBy", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(VisitOrdering visitOrdering) {
		return (visitOrdering.getOrderBy() != null && visitOrdering.getOrderDirection() == null)
				? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
				: Optional.empty();
	}

}
