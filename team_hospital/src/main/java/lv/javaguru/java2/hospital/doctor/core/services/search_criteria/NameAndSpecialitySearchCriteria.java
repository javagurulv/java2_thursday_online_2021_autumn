package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class NameAndSpecialitySearchCriteria implements DoctorsSearchCriteria {

    private final DoctorDatabase database;

    public NameAndSpecialitySearchCriteria(DoctorDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchDoctorsRequest request) {
        return request.isNameProvided()
                && request.isSpecialityProvided()
                && !request.isIdProvided()
                && !request.isSurnameProvided();
    }

    @Override
    public List<Doctor> process(SearchDoctorsRequest request) {
        return database.findByNameAndSpeciality(request.getName(), request.getSpeciality());
    }
}