package lv.javaguru.java2.hospital.database.patient_repository;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    void save(Patient patient);

    Optional<Patient> findById(Long id);

    boolean deleteById(Long id);

    List<Patient> findAll();

    boolean editPatient(Long patientID, String userInput, String input);

    List<Patient> findByName(String name);

    List<Patient> findBySurname(String surname);

    List<Patient> findByPersonalCode(String personalCode);

    List<Patient> findByNameAndSurname(String name, String surname);

    List<Patient> findByNameAndPersonalCode(String name, String personal_code);

    List<Patient> findBySurnameAndPersonalCode(String surname, String personal_code);

    List<Patient> findByNameAndSurnameAndPersonalCode(String name, String surname, String personal_code);

}
