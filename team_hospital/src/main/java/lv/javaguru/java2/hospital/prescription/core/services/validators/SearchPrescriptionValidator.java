package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchPrescriptionValidator {

    @Autowired private SearchPrescriptionFieldValidator fieldValidator;

    public List<CoreError> validate(SearchPrescriptionRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        return errors;
    }

}
