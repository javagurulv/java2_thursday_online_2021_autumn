package lv.javaguru.java2.hospital.doctor.services;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

import java.util.List;

public class ShowAllDoctorsService {
    private final DoctorDatabaseImpl database;

    public ShowAllDoctorsService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public List<Doctor> execute() {
        return database.showAllDoctors();
    }
}
