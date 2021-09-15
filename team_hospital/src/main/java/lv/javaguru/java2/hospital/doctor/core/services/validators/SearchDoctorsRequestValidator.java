package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.Ordering;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchDoctorsRequestValidator {

    public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if(request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getId()) && isEmpty(request.getName()) && isEmpty(request.getSurname()) && isEmpty(request.getSpeciality())) {
            errors.add(new CoreError("id", "Must not be empty!"));
            errors.add(new CoreError("name", "Must not be empty!"));
            errors.add(new CoreError("surname", "Must not be empty!"));
            errors.add(new CoreError("speciality", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null)
                && !(ordering.getOrderBy().equals("name") || ordering.getOrderBy().equals("surname") || ordering.getOrderBy().equals("speciality"))
                ? Optional.of(new CoreError("orderBy", "Must contain 'name', 'surname' or 'speciality' only!"))
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

}
