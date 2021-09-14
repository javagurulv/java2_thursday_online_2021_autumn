package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorsRequestValidator {

    public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
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
}
