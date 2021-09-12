package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

import java.util.List;

public class ShowAllDoctorsService {
    private final DoctorDatabaseImpl database;

    public ShowAllDoctorsService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public ShowAllDoctorsResponse execute(ShowAllDoctorsRequest request) {
        List<Doctor> doctors = database.showAllDoctors();
        return new ShowAllDoctorsResponse(doctors);
    }
}
