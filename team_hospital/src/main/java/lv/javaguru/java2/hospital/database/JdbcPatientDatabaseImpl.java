package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcPatientDatabaseImpl implements PatientDatabase{

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Patient patient) {
        jdbcTemplate.update(
                "INSERT INTO patients (name, surname, personalCode) "
                        + "VALUES (?, ?, ?)",
                patient.getName(), patient.getSurname(), patient.getPersonalCode()
        );
    }

    @Override
    public List<Patient> findById(Long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Patient> getAllPatients() {
        return null;
    }

    @Override
    public boolean editActions(Long patientID, Enum userInput, String input) {
        return false;
    }

    @Override
    public boolean patientExists(Long id) {
        return false;
    }

    @Override
    public List<Patient> findPatientsByName(String name) {
        return null;
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        return null;
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        return null;
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personalCode) {
        return null;
    }

    @Override
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personalCode) {
        return null;
    }

    @Override
    public List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personalCode) {
        return null;
    }
}
