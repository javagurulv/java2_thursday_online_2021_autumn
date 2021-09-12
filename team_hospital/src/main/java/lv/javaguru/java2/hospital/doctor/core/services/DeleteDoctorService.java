package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.DeleteDoctorValidator;

import java.util.List;

public class DeleteDoctorService {
    private final DoctorDatabaseImpl database;
    private final DeleteDoctorValidator validator;

    public DeleteDoctorService(DoctorDatabaseImpl database, DeleteDoctorValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteDoctorResponse execute(DeleteDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDoctorResponse(errors);
        }
        boolean isDoctorDeleted = database.deleteDoctorById(request.getDoctorIdToDelete());
        return new DeleteDoctorResponse(isDoctorDeleted);
    }
}
