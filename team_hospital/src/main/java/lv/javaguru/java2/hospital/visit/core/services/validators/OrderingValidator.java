package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderingValidator {

	public List<CoreError> validate(Ordering ordering) {
		List<CoreError> errors = new ArrayList<>();
		validateOrderBy(ordering).ifPresent(errors::add);
		validateOrderDirection(ordering).ifPresent(errors::add);
		validateMandatoryOrderBy(ordering).ifPresent(errors::add);
		validateMandatoryOrderDirection(ordering).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateOrderBy(Ordering ordering) {
		return (ordering.getOrderBy() != null)
				&& !(ordering.getOrderBy().equals("name") || ordering.getOrderBy().equals("surname") || ordering.getOrderBy().equals("speciality"))
				? Optional.of(new CoreError("orderBy", "Must contain 'name', 'surname' or 'speciality' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(Ordering ordering) {
		return (ordering.getOrderDirection() != null
				&& !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
				? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
		return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
				? Optional.of(new CoreError("orderBy", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
		return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
				? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
				: Optional.empty();
	}

}
