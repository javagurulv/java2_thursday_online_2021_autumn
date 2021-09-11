package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindSpecialistValidator {


    public List<CoreError> validate(FindSpecialistRequest request) {

        List<CoreError> errors = new ArrayList<>();
//        validateId(request).ifPresent(errors::add);
//        validateName(request).ifPresent(errors::add);
//        validateSurname(request).ifPresent(errors::add);
//        validateProfession(request).ifPresent(errors::add);
        errors.addAll(validateSearchFields(request));
        //errors.addAll(validateProfessionNotContainsNumbers(request));
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
    private List<CoreError> validateSearchFields(FindSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getSpecialistName()) && isEmpty(request.getSpecialistSurname())&& isEmpty(request.getSpecialistProfession())) {
            errors.add(new CoreError("specialistName", "Must not be empty!"));
            errors.add(new CoreError("specialistSurname", "Must not be empty!"));
            errors.add(new CoreError("specialistProfession", "Must not be empty!"));
        }
        if(request.getSpecialistName().matches("[0-9]+") && request.getSpecialistSurname().matches("[0-9]+") && request.getSpecialistProfession().matches("[0-9]+")) {
            errors.add(new CoreError("specialistName", "Must not contains numbers!"));
            errors.add(new CoreError("specialistSurname", "Must not contains numbers!"));
            errors.add(new CoreError("specialistProfession", "Must not contains numbers!"));
        }
            return errors;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


//    private Optional<CoreError> validateId(FindSpecialistRequest request) {
//        return (isEmpty(String.valueOf(request.getSpecialistId())))
//                ? Optional.of(new CoreError("ID", "Must not be empty!"))
//               : Optional.empty();
//    }
//
//
//    private Optional<CoreError> validateName(FindSpecialistRequest request) {
//        return (isEmpty(request.getSpecialistName()))
//                ? Optional.of(new CoreError("NAME", "Must not be empty!"))
//                : Optional.empty();
//    }
//
//
//    private Optional<CoreError> validateSurname(FindSpecialistRequest request) {
//        return (isEmpty(request.getSpecialistSurname()))
//                ? Optional.of(new CoreError("SURNAME", "Must not be empty!"))
//                : Optional.empty();
//    }
//
//    private Optional<CoreError> validateProfession(FindSpecialistRequest request) {
//        return (isEmpty(request.getSpecialistProfession()))
//                ? Optional.of(new CoreError("Profession", "Must not be empty!"))
//                : Optional.empty();
   // }

//    private List<CoreError> validateProfessionNotContainsNumbers(FindSpecialistRequest request) {
//        List<CoreError> errors = new ArrayList<>();
//        if (request.getSpecialistProfession().matches("[0-9]+"))
//            errors.add(new CoreError("specialistProfession", "Doesn't contains numbers!"));
//
//        return errors;
//    }




    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("specialistName") || ordering.getOrderBy().equals("specialistSurname") || ordering.getOrderBy().equals("specialistProfession")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'specialistName' or 'specialistSurname' or 'specialistProfession' only!"))
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
}
