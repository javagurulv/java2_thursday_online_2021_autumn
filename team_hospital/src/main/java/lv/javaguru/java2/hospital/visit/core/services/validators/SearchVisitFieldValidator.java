package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchVisitFieldValidator {

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isLongEmpty(request.getVisitId()) && isLongEmpty(request.getDoctorId())
                && isLongEmpty(request.getPatientId()) && isStringEmpty(request.getVisitDate())) {
            errors.add(new CoreError("visitId", "Must not be empty!"));
            errors.add(new CoreError("doctorId", "Must not be empty!"));
            errors.add(new CoreError("patientId", "Must not be empty!"));
            errors.add(new CoreError("visitDate", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isStringEmpty(String date) {
        return date == null;
    }

    private boolean isLongEmpty(Long num) {
        return num == null;
    }
}
