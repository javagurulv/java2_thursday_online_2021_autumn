package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDatabase {
    void add(Patient patient);
    Optional<Patient> findById(Long id);
    void deleteById(Long id);
    List<Patient> showAllPatients();
    void editActions(Long id, int userInput, String input);
    boolean patientExists(Long id);
}
