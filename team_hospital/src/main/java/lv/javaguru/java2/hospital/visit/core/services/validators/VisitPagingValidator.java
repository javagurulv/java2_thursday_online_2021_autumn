package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitIntegerNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VisitPagingValidator {

    @Autowired
    private VisitIntegerNumChecker numChecker;

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request != null) {
            validatePageNumberForNum(request).ifPresent(errors::add);
            validatePageSizeForNum(request).ifPresent(errors::add);
            if (errors.isEmpty()) {
                validatePageNumber(request).ifPresent(errors::add);
                validatePageSize(request).ifPresent(errors::add);
                //validateMandatoryPageNumber(visitPaging).ifPresent(errors::add);
                //validateMandatoryPageSize(visitPaging).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validatePageNumberForNum(SearchVisitRequest request) {
        return request.getPageNumber() == null || request.getPageNumber().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getPageNumber(), "Page number");
    }

    private Optional<CoreError> validatePageSizeForNum(SearchVisitRequest request) {
        return request.getPageSize() == null || request.getPageSize().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getPageSize(), "Page size");
    }

    private Optional<CoreError> validatePageNumber(SearchVisitRequest request) {
        return request.getPageNumber() != null && !request.getPageNumber().isEmpty()
                && Integer.parseInt(request.getPageNumber()) <= 0
                ? Optional.of(new CoreError("Page number", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(SearchVisitRequest request) {
        return (request.getPageSize() != null && !request.getPageSize().isEmpty()
                && Integer.parseInt(request.getPageSize()) <= 0)
                ? Optional.of(new CoreError("Page size", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(SearchVisitRequest request) {
        return (request.getPageNumber() == null && request.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(SearchVisitRequest request) {
        return (request.getPageSize() == null && request.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }
}