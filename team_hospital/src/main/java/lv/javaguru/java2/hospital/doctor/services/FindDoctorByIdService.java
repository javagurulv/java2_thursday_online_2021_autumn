package lv.javaguru.java2.hospital.doctor.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

public class FindDoctorByIdService {
    private final DoctorDatabaseImpl database;

    public FindDoctorByIdService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int id) {
        database.findDoctorById(id);
    }
}
