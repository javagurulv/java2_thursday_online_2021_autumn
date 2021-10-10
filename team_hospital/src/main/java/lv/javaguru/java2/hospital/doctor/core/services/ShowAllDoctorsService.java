package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllDoctorsService {

    @Autowired private DoctorDatabaseImpl database;

    public ShowAllDoctorsResponse execute(ShowAllDoctorsRequest request) {
        List<Doctor> doctors = database.showAllDoctors();
        return new ShowAllDoctorsResponse(doctors);
    }
}
