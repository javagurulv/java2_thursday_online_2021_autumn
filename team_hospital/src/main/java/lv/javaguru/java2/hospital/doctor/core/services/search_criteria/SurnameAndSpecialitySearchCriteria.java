package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class SurnameAndSpecialitySearchCriteria implements DoctorsSearchCriteria{

    private final DoctorDatabase database;

    public SurnameAndSpecialitySearchCriteria(DoctorDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchDoctorsRequest request) {
        return request.isSurnameProvided()
                && request.isSpecialityProvided()
                && !request.isIdProvided()
                && !request.isNameProvided();
    }

    @Override
    public List<Doctor> process(SearchDoctorsRequest request) {
        return database.findBySurnameAndSpeciality(request.getSurname(), request.getSpeciality());
    }
}
