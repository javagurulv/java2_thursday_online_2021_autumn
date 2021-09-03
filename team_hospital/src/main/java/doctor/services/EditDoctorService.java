package doctor.services;

import database.DoctorDatabaseImpl;

public class EditDoctorService {
    private final DoctorDatabaseImpl database;

    public EditDoctorService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int doctorId, int userInput, String changes) {
        database.editDoctor(doctorId, userInput, changes);
    }
}
