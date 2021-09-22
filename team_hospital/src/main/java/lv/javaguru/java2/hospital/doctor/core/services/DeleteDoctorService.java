package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.DeleteDoctorRequestValidator;

import java.util.List;

public class DeleteDoctorService {
    private final DoctorDatabaseImpl database;
    private final DeleteDoctorRequestValidator validator;

    public DeleteDoctorService(DoctorDatabaseImpl database, DeleteDoctorRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteDoctorResponse execute(DeleteDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDoctorResponse(errors);
        }
        boolean isDoctorDeleted = database.deleteDoctorById(Long.parseLong(request.getDoctorIdToDelete()));
        return new DeleteDoctorResponse(isDoctorDeleted);
    }
}
