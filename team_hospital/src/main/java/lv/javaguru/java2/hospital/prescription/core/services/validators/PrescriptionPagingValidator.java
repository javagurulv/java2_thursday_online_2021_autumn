package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PrescriptionPagingValidator {

    public List<CoreError> validate(PrescriptionPaging paging) {
        List<CoreError> errors = new ArrayList<>();
        validatePageNumber(paging).ifPresent(errors::add);
        validatePageSize(paging).ifPresent(errors::add);
        validateMandatoryPageNumber(paging).ifPresent(errors::add);
        validateMandatoryPageSize(paging).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePageNumber(PrescriptionPaging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(PrescriptionPaging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(PrescriptionPaging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                :Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(PrescriptionPaging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }
}
