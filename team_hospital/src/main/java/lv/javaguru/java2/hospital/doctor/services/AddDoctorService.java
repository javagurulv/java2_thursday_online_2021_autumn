package lv.javaguru.java2.hospital.doctor.services;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

public class AddDoctorService {
    private final DoctorDatabaseImpl database;

    public AddDoctorService(DoctorDatabaseImpl database) {
        this.database = database;
    }

    public void execute(String name, String surname, String speciality) {
        Doctor doctor = new Doctor(name, surname, speciality);
        database.addDoctor(doctor);
    }
}
