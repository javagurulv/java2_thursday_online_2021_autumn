package patient.services;

import patient.database.PatientDatabaseImpl;

public class PatientExistsService {
    private final PatientDatabaseImpl database;

    public PatientExistsService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public boolean execute(int id){
        return database.patientExists(id);
    }
}
