package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.DeleteDoctorRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteDoctorService {

    @Autowired private DoctorDatabaseImpl database;
    @Autowired private DeleteDoctorRequestValidator validator;

    public DeleteDoctorResponse execute(DeleteDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDoctorResponse(errors);
        }
        boolean isDoctorDeleted = database.deleteDoctorById(request.getDoctorIdToDelete());
        return new DeleteDoctorResponse(isDoctorDeleted);
    }
}
