package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDatabase {
    void add(Patient patient);
    Optional<Patient> findById(Long id);
    boolean deleteById(Long id);
    List<Patient> showAllPatients();
    void editActions(Long id, int userInput, String input);
    boolean patientExists(Long id);
    List<Patient> findPatientsByName(String name);
    List<Patient> findPatientsBySurname(String surname);
    List<Patient> findPatientsByPersonalCode(String personalCode);
    List<Patient> findPatientsByNameAndSurname(String name, String surname);
    List<Patient> findPatientsByNameAndPersonalCode(String name, String personalCode);
    List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personalCode);
}
