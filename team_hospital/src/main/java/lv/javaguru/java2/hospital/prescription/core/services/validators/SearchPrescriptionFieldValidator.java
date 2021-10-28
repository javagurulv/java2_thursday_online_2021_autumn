package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchPrescriptionFieldValidator {

    public List<CoreError> validate(SearchPrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if(isLongEmpty(request.getPrescriptionId())
                && isLongEmpty(request.getDoctorId())
                && isLongEmpty(request.getPatientId())) {
            errors.add(new CoreError("prescriptionId", "Must not be empty!"));
            errors.add(new CoreError("doctorId", "Must not be empty!"));
            errors.add(new CoreError("patientId", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isLongEmpty(Long num) {
        return num == null;
    }
}
