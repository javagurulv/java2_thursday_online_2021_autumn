package patient.services;

import database.PatientDatabaseImpl;

public class EditPatientService {
    private final PatientDatabaseImpl database;

    public EditPatientService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public void execute(int patientID, int userInput, String changes) {
        database.editActions(patientID, userInput, changes);
    }
}
