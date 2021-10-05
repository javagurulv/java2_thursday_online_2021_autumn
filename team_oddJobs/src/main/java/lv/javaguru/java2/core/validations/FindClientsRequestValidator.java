package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class FindClientsRequestValidator {

    public List<CoreError> validate(FindClientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }

        if (request.getPaging() != null) {
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }


//    private Optional<CoreError> validateName(FindClientsRequest request) {
//        return (request.getClientName() != null)
//                ? Optional.of(new CoreError("NAME", "Must not be empty!"))
//                : Optional.empty();
//    }
//
//    private Optional<CoreError> validateSurname(FindClientsRequest request) {
//        return (request.getClientSurname() == null)
//                ? Optional.of(new CoreError("SURNAME", "Must not be empty!"))
//                : Optional.empty();
//    }

    private List<CoreError> validateSearchFields(FindClientsRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isIdEmpty(request.getClientId())) {
            errors.add(new CoreError("ID", "Must not be empty!"));
        }

        if (isNameOrSurnameEmpty(request.getClientName())) {
            errors.add(new CoreError("NAME", "Must not be empty!"));
        }

        if (isNameOrSurnameEmpty(request.getClientSurname())) {
            errors.add(new CoreError("SURNAME", "Must not be empty!"));
        }
        return errors;
    }


    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("ID") || ordering.getOrderBy().equals("NAME") || ordering.getOrderBy().equals("SURNAME")))
                ? Optional.of(new CoreError("orderBy", "Must contain id,name or surname"))
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

    private Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }


    private boolean isNameOrSurnameEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isIdEmpty(Long id) {
        return id == null;
    }


}
