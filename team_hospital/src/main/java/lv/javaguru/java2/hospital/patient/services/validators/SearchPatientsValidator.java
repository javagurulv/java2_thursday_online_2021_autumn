package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.Ordering;
import lv.javaguru.java2.hospital.patient.requests.Paging;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.*;

public class SearchPatientsValidator {
    public List<CoreError> validate(SearchPatientsRequest request) {
        return new ArrayList<>(validateEmptyFields(request));
    }

    private List<CoreError> validateEmptyFields(SearchPatientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }

        if(request.getPaging() != null){
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchPatientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getSurname())
                && isEmpty(request.getPersonalCode())) {
            errors.add(new CoreError("Name", "must not be empty!"));
            errors.add(new CoreError("Surname", "must not be empty!"));
            errors.add(new CoreError("Personal code", "must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering){
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().toLowerCase(Locale.ROOT).equals("name")
                || ordering.getOrderBy().toLowerCase(Locale.ROOT).equals("surname")))
                ? Optional.of(new CoreError("Order By", "must contain 'NAME' or 'SURNAME' only!"))
                : Optional.empty();

    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().toUpperCase(Locale.ROOT).equals("ASCENDING")
                || ordering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")))
                ? Optional.of(new CoreError("Order Direction", "must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("Order By", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "must not be empty!"))
                : Optional.empty();
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
