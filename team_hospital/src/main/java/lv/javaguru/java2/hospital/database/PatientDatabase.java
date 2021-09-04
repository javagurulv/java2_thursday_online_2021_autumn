package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;

public interface PatientDatabase {
    void add(Patient patient);
    void findById(int id);
    void deleteById(int id);
    List<Patient> showAllPatients();
    void editActions(int patientID, int userInput, String input);
    boolean patientExists(int id);
}
