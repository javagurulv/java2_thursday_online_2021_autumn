package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllDoctorsService {

    @Autowired private DoctorDatabase database;

    public ShowAllDoctorsResponse execute(ShowAllDoctorsRequest request) {
        List<Doctor> doctors = database.getAllDoctors();
        return new ShowAllDoctorsResponse(doctors);
    }
}
