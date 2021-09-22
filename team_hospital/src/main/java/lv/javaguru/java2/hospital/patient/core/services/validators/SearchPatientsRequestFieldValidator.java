package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientsRequestFieldValidator {
    public List<CoreError> validate(SearchPatientsRequest request) {
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
}


