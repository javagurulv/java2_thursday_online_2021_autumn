package patient.services;

import patient.database.PatientDatabaseImpl;

public class DeletePatientService {
    private final PatientDatabaseImpl database;

    public DeletePatientService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public void execute(int id) {
        database.deleteById(id);
    }
}
