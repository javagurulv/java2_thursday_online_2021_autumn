package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public class NameSearchCriteria implements DoctorsSearchCriteria {

    private final DoctorRepository database;

    public NameSearchCriteria(DoctorRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchDoctorsRequest request) {
        return request.isNameProvided()
                && !request.isIdProvided()
                && !request.isSurnameProvided()
                && !request.isSpecialityProvided();
    }

    @Override
    public List<Doctor> process(SearchDoctorsRequest request) {
        return database.findByName(request.getName());
    }
}
