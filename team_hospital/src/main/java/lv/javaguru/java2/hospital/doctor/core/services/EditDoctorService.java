package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.EditDoctorRequestValidator;

import java.util.List;

@DIComponent
public class EditDoctorService {
    @DIDependency private DoctorDatabaseImpl database;
    @DIDependency private EditDoctorRequestValidator validator;

    public EditDoctorResponse execute(EditDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditDoctorResponse(errors);
        }

        boolean isDoctorEdited = database.editDoctor(request.getDoctorId(), request.getUserInput(), request.getChanges());
        return new EditDoctorResponse(isDoctorEdited);
    }
}
