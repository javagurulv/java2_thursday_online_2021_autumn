package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;

import java.util.List;

public interface PatientDatabase {
    void add(Patient patient);

    List<Patient> findById(Long id);

    boolean deleteById(Long id);

    List<Patient> getAllPatients();

    boolean editActions(Long patientID, EditPatientEnum userInput, String input);

    List<Patient> findPatientsByName(String name);

    List<Patient> findPatientsBySurname(String surname);

    List<Patient> findPatientsByPersonalCode(String personalCode);

    List<Patient> findPatientsByNameAndSurname(String name, String surname);

    List<Patient> findPatientsByNameAndPersonalCode(String name, String personal_code);

    List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personal_code);

    List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personal_code);

}
