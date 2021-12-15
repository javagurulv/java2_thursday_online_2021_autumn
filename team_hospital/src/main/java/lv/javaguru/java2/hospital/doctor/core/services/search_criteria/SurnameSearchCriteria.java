package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class SurnameSearchCriteria implements DoctorsSearchCriteria{

    private final DoctorRepository database;

    public SurnameSearchCriteria(DoctorRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchDoctorsRequest request) {
        return request.isSurnameProvided()
                && !request.isNameProvided()
                && !request.isSpecialityProvided();
    }

    @Override
    public List<Doctor> process(SearchDoctorsRequest request) {
        return database.findBySurname(request.getSurname());
    }
}
