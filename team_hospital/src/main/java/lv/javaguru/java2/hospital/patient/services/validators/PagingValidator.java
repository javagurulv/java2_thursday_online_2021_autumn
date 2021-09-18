package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.Paging;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagingValidator {
    public List<CoreError> validate(Paging paging){
        List<CoreError> errors = new ArrayList<>();
        if(paging != null) {
            validatePageNumber(paging).ifPresent(errors::add);
            validatePageSize(paging).ifPresent(errors::add);
            validateMandatoryPageSize(paging).ifPresent(errors::add);
            validateMandatoryPageNumber(paging).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && Integer.parseInt(paging.getPageNumber()) <= 0)
                ? Optional.of(new CoreError("pageNumber", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && Integer.parseInt(paging.getPageSize()) <= 0)
                ? Optional.of(new CoreError("pageSize", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "must not be empty!"))
                : Optional.empty();
    }
}
