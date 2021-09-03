package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;

public class AddPatientService {
    private final PatientDatabaseImpl database;

    public AddPatientService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public void execute(String name, String surname, String personalCode) {
        Patient patient = new Patient(name, surname, personalCode);
        database.add(patient);
    }
}
