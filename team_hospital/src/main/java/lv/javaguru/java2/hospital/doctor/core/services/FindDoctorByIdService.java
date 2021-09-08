package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.FindDoctorByIdRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.FindDoctorByIdResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.FindDoctorByIdValidator;

import java.util.List;

public class FindDoctorByIdService {
    private final DoctorDatabaseImpl database;
    private final FindDoctorByIdValidator validator;

    public FindDoctorByIdService(DoctorDatabaseImpl database, FindDoctorByIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindDoctorByIdResponse execute(FindDoctorByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindDoctorByIdResponse(errors);
        }
        boolean isDoctorFound = database.findDoctorById(request.getDoctorId());
        return new FindDoctorByIdResponse(isDoctorFound);

    }
}
