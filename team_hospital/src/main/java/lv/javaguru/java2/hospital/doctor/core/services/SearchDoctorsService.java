package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.SearchDoctorsRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class SearchDoctorsService {

    private DoctorDatabase database;
    private SearchDoctorsRequestValidator validator;

    public SearchDoctorsService(DoctorDatabase database, SearchDoctorsRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchDoctorsResponse execute(SearchDoctorsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new SearchDoctorsResponse(null, errors);
        }

        List<Doctor> doctors = null;
        if(request.isNameProvided() && !request.isSurnameProvided()) {
            doctors = database.findByName(request.getName());
        }
        if(!request.isNameProvided() && request.isSurnameProvided()) {
            doctors = database.findBySurname(request.getSurname());
        }
        if(request.isNameProvided() && request.isSurnameProvided()) {
            doctors = database.findByNameAndSurname(request.getName(), request.getSurname());
        }
        return new SearchDoctorsResponse(doctors, null);
    }
}
