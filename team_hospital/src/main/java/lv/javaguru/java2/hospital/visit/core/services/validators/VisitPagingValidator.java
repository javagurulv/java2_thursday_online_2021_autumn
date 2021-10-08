package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitPagingValidator {

	public List<CoreError> validate(VisitPaging visitPaging) {
		List<CoreError> errors = new ArrayList<>();
		validatePageNumber(visitPaging).ifPresent(errors::add);
		validatePageSize(visitPaging).ifPresent(errors::add);
		validateMandatoryPageNumber(visitPaging).ifPresent(errors::add);
		validateMandatoryPageSize(visitPaging).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validatePageNumber(VisitPaging visitPaging) {
		return (visitPaging.getPageNumber() != null
				&& visitPaging.getPageNumber() <= 0)
				? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validatePageSize(VisitPaging visitPaging) {
		return (visitPaging.getPageSize() != null
				&& visitPaging.getPageSize() <= 0)
				? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageNumber(VisitPaging visitPaging) {
		return (visitPaging.getPageNumber() == null && visitPaging.getPageSize() != null)
				? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
				:Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageSize(VisitPaging visitPaging) {
		return (visitPaging.getPageSize() == null && visitPaging.getPageNumber() != null)
				? Optional.of(new CoreError("pageSize", "Must not be empty!"))
				: Optional.empty();
	}

}