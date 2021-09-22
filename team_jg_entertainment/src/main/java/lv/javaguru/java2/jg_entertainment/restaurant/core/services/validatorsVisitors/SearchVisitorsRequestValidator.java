package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchVisitorsRequestValidator {

    public List<CoreError> validator(SearchVisitorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validatorSearchField(request));
        if (request.getOrdering() != null) {
            validatorOrderBy(request.getOrdering()).ifPresent(errors::add);
            validatorOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validatorMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validatorMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validatorSearchField(SearchVisitorsRequest request) {//telephone
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getNameVisitors()) && isEmpty(request.getSurnameVisitors())) {
            errors.add(new CoreError("name", "can not be empty!"));
            errors.add(new CoreError("surname", "can not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validatorOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("name")
                || ordering.getOrderBy().equals("surname")))
                ? Optional.of(new CoreError("orderBy", "might contain 'name' or 'surname' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING")
                || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "might contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "can not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "can not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageNumber(Paging paging) {//paging
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null
                && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null
                && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "must not be empty!"))
                : Optional.empty();
    }
}
