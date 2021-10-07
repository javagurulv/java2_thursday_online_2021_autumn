package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class ValidatorPaging {

    public List<CoreError> validatorPaging(PagingTable paging) {
        List<CoreError> coreErrors = new ArrayList<>();
        validatePageNumber(paging).ifPresent(coreErrors::add);
        validatePageSize(paging).ifPresent(coreErrors::add);
        validateMandatoryPageNumber(paging).ifPresent(coreErrors::add);
        validateMandatoryPageSize(paging).ifPresent(coreErrors::add);
        return coreErrors;
    }

    private Optional<CoreError> validatePageNumber(PagingTable paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "must be greater than 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(PagingTable paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "must be greater than 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(PagingTable paging) {
        return (paging.getPageNumber() == null
                && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(PagingTable paging) {
        return (paging.getPageSize() == null
                && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "must not be empty!"))
                : Optional.empty();
    }
}
