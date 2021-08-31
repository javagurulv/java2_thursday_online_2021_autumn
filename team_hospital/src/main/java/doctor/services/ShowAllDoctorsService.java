package doctor.services;

import doctor.Doctor;
import doctor.database.DoctorDatabaseImpl;

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
