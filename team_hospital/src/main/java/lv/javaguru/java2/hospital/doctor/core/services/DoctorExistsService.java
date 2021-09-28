package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

public class DoctorExistsService {
    private final DoctorDatabaseImpl database;

    public DoctorExistsService(DoctorDatabaseImpl database) {
        this.database = database;
    }

    public boolean execute(Long id) {
        return database.doctorExists(id);
    }
}
