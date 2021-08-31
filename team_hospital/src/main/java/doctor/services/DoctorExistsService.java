package doctor.services;

import doctor.database.DoctorDatabaseImpl;

public class DoctorExistsService {
    private final DoctorDatabaseImpl database;

    public DoctorExistsService(DoctorDatabaseImpl database) {
        this.database = database;
    }

    public boolean execute(int id) {
        return database.doctorExists(id);
    }
}
