package patient.services;

import patient.Patient;
import patient.database.PatientDatabaseImpl;
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
