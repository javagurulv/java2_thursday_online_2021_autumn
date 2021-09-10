package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;

public class PatientExistsService {
    private final PatientDatabaseImpl database;

    public PatientExistsService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public boolean execute(Long id){
        return database.patientExists(id);
    }
}
