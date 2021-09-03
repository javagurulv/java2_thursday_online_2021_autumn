package patient.services;

import database.PatientDatabaseImpl;

public class PatientExistsService {
    private final PatientDatabaseImpl database;

    public PatientExistsService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public boolean execute(int id){
        return database.patientExists(id);
    }
}
