package lv.javaguru.java2.hospital.doctor.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

public class EditDoctorService {
    private final DoctorDatabaseImpl database;

    public EditDoctorService(DoctorDatabaseImpl doctorDatabase) {
        this.database = doctorDatabase;
    }

    public void execute(int doctorId, int userInput, String changes) {
        database.editDoctor(doctorId, userInput, changes);
    }
}
