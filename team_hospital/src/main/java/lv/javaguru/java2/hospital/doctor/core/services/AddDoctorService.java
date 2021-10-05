package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.AddDoctorRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

import java.util.List;

@DIComponent
public class AddDoctorService {

    @DIDependency private DoctorDatabaseImpl database;
    @DIDependency private AddDoctorRequestValidator validator;

    public AddDoctorResponse execute(AddDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDoctorResponse(errors);
        }



        Doctor doctor = new Doctor(request.getName(), request.getSurname(), request.getSpeciality());
        database.addDoctor(doctor);
        return new AddDoctorResponse(doctor);
    }
}
