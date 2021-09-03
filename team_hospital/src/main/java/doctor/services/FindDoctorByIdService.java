package doctor.services;

import database.DoctorDatabaseImpl;

public class FindDoctorByIdService {
    private final DoctorDatabaseImpl database;

    public FindDoctorByIdService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int id) {
        database.findDoctorById(id);
    }
}
