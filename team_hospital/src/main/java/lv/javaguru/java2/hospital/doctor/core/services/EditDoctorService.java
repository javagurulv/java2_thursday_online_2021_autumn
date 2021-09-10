package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.EditDoctorValidator;

import java.util.List;

public class EditDoctorService {
    private final DoctorDatabaseImpl database;
    private final EditDoctorValidator validator;

    public EditDoctorService(DoctorDatabaseImpl database, EditDoctorValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public EditDoctorResponse execute(EditDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditDoctorResponse(errors);
        }

        boolean isDoctorEdited = database.editDoctor(request.getDoctorId(), request.getUserInput(), request.getChanges());
        return new EditDoctorResponse(isDoctorEdited);
    }
}
