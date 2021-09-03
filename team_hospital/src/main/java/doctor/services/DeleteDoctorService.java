package doctor.services;

import database.DoctorDatabaseImpl;

public class DeleteDoctorService {
    private final DoctorDatabaseImpl database;

    public DeleteDoctorService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int id) {
        database.deleteDoctorById(id);
    }
}
