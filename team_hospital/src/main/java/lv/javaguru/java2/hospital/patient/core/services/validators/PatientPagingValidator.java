package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PatientPagingValidator {

    @Autowired private PatientLongNumChecker numChecker;

    public List<CoreError> validate(SearchPatientsRequest request){
        List<CoreError> errors = new ArrayList<>();
        if(request != null) {
            validatePageNumberForNum(request).ifPresent(errors::add);
            validatePageSizeForNum(request).ifPresent(errors::add);
            if(errors.isEmpty()){
                validatePageNumber(request).ifPresent(errors::add);
                validatePageSize(request).ifPresent(errors::add);
                //validateMandatoryPageSize(patientPaging).ifPresent(errors::add);
                //validateMandatoryPageNumber(patientPaging).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validatePageNumberForNum(SearchPatientsRequest request){
        return request.getPageNumber() == null || request.getPageNumber().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getPageNumber(), "Page number");
    }

    private Optional<CoreError> validatePageSizeForNum(SearchPatientsRequest request){
        return request.getPageSize() == null || request.getPageSize().isEmpty()
                ? Optional.empty() : numChecker.validate(request.getPageSize(), "Page size");
    }

    private Optional<CoreError> validatePageNumber(SearchPatientsRequest request) {
        return request.getPageNumber() != null && !request.getPageNumber().isEmpty()
                && Integer.parseInt(request.getPageNumber()) <= 0
                ? Optional.of(new CoreError("Page number", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(SearchPatientsRequest request) {
        return (request.getPageSize() != null && !request.getPageSize().isEmpty()
                && Integer.parseInt(request.getPageSize()) <= 0)
                ? Optional.of(new CoreError("Page size", "must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(SearchPatientsRequest request) {
        return (request.getPageNumber() == null && request.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(SearchPatientsRequest request) {
        return (request.getPageSize() == null && request.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "must not be empty!"))
                : Optional.empty();
    }
}
