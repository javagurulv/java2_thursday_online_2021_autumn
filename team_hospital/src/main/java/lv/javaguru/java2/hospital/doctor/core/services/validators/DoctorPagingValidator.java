package lv.javaguru.java2.hospital.doctor.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.springframework.stereotype.Component;

@Component
public class DoctorPagingValidator {

	public List<CoreError> validate(DoctorPaging doctorPaging) {
		List<CoreError> errors = new ArrayList<>();
		validatePageNumber(doctorPaging).ifPresent(errors::add);
		validatePageSize(doctorPaging).ifPresent(errors::add);
		validateMandatoryPageNumber(doctorPaging).ifPresent(errors::add);
		validateMandatoryPageSize(doctorPaging).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validatePageNumber(DoctorPaging doctorPaging) {
		return (doctorPaging.getPageNumber() != null
				&& doctorPaging.getPageNumber() <= 0)
				? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validatePageSize(DoctorPaging doctorPaging) {
		return (doctorPaging.getPageSize() != null
				&& doctorPaging.getPageSize() <= 0)
				? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageNumber(DoctorPaging doctorPaging) {
		return (doctorPaging.getPageNumber() == null && doctorPaging.getPageSize() != null)
				? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
				:Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageSize(DoctorPaging doctorPaging) {
		return (doctorPaging.getPageSize() == null && doctorPaging.getPageNumber() != null)
				? Optional.of(new CoreError("pageSize", "Must not be empty!"))
				: Optional.empty();
	}

}