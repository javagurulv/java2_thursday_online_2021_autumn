package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import java.util.List;

public class ShowAllPatientsService {
    private final PatientDatabaseImpl database;

    public ShowAllPatientsService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public List<Patient> execute() {
        return database.showAllPatients();
    }
}
