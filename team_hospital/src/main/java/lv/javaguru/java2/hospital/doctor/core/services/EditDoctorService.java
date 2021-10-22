package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.EditDoctorRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditDoctorService {

    @Autowired private DoctorDatabase database;
    @Autowired private EditDoctorRequestValidator validator;

    public EditDoctorResponse execute(EditDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditDoctorResponse(errors);
        }

        boolean isDoctorEdited = database.editDoctor(request.getDoctorId(), request.getUserInputEnum(), request.getChanges());
        return new EditDoctorResponse(isDoctorEdited);
    }
}
