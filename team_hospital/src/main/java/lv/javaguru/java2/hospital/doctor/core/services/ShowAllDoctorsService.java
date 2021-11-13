package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ShowAllDoctorsService {

    @Autowired private DoctorRepository database;

    public ShowAllDoctorsResponse execute(ShowAllDoctorsRequest request) {
        List<Doctor> doctors = database.getAllDoctors();
        return new ShowAllDoctorsResponse(doctors);
    }
}
