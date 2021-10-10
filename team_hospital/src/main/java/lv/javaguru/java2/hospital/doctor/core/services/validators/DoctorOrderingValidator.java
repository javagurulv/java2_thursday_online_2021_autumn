package lv.javaguru.java2.hospital.doctor.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.springframework.stereotype.Component;

@Component
public class DoctorOrderingValidator {

	public List<CoreError> validate(DoctorOrdering doctorOrdering) {
		List<CoreError> errors = new ArrayList<>();
		validateOrderBy(doctorOrdering).ifPresent(errors::add);
		validateOrderDirection(doctorOrdering).ifPresent(errors::add);
		validateMandatoryOrderBy(doctorOrdering).ifPresent(errors::add);
		validateMandatoryOrderDirection(doctorOrdering).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateOrderBy(DoctorOrdering doctorOrdering) {
		return (doctorOrdering.getOrderBy() != null)
				&& !(doctorOrdering.getOrderBy().equals("name") || doctorOrdering.getOrderBy().equals("surname") || doctorOrdering.getOrderBy().equals("speciality"))
				? Optional.of(new CoreError("orderBy", "Must contain 'name', 'surname' or 'speciality' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(DoctorOrdering doctorOrdering) {
		return (doctorOrdering.getOrderDirection() != null
				&& !(doctorOrdering.getOrderDirection().equals("ASCENDING") || doctorOrdering.getOrderDirection().equals("DESCENDING")))
				? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(DoctorOrdering doctorOrdering) {
		return (doctorOrdering.getOrderDirection() != null && doctorOrdering.getOrderBy() == null)
				? Optional.of(new CoreError("orderBy", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(DoctorOrdering doctorOrdering) {
		return (doctorOrdering.getOrderBy() != null && doctorOrdering.getOrderDirection() == null)
				? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
				: Optional.empty();
	}

}
