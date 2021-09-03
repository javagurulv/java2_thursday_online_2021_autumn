package lv.javaguru.java2.hospital.doctor.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

public class DeleteDoctorService {
    private final DoctorDatabaseImpl database;

    public DeleteDoctorService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int id) {
        database.deleteDoctorById(id);
    }
}
