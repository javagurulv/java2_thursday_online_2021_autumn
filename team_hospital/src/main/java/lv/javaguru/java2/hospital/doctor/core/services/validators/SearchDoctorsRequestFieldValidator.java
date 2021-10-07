package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchDoctorsRequestFieldValidator {

    public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isLongEmpty(request.getId()) && isStringEmpty(request.getName())
                && isStringEmpty(request.getSurname()) && isStringEmpty(request.getSpeciality())) {
            errors.add(new CoreError("id", "Must not be empty!"));
            errors.add(new CoreError("name", "Must not be empty!"));
            errors.add(new CoreError("surname", "Must not be empty!"));
            errors.add(new CoreError("speciality", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isLongEmpty(Long num) {
        return num == null;
    }
}
