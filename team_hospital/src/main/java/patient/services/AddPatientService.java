package patient.services;

import patient.Patient;
import patient.database.PatientDatabaseImpl;

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
