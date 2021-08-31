package patient.database;

import patient.Patient;

import java.util.List;

public interface PatientDatabase {
    void add(Patient patient);
    void findById(int id);
    void deleteById(int id);
    List<Patient> showAllPatients();
    void editActions(int patientID, int userInput, String input);
    boolean patientExists(int id);
}
