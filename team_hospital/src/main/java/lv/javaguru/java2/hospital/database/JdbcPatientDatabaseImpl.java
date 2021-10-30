package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//@Component
public class JdbcPatientDatabaseImpl implements PatientDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM patients";
        return jdbcTemplate.query(sql, new PatientRawMapper());
    }

    @Override
    public boolean editActions(Long patientID, EditPatientEnum userInput, String input) {
        String chosenEnum;
        switch (userInput) {
            case NAME -> chosenEnum = "name";
            case SURNAME -> chosenEnum = "surname";
            case PERSONAL_CODE -> chosenEnum = "personal_code";
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
        String sql = "UPDATE patients SET " + chosenEnum + " = ? WHERE id = ?";
        return jdbcTemplate.update(sql, input, patientID) == 1;
    }

    @Override
    public List<Patient> findPatientsByName(String name) {
        String sql = "SELECT * FROM patients WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        String sql = "SELECT * FROM patients WHERE surname = ?";
        Object[] args = new Object[]{surname};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        String sql = "SELECT * FROM patients WHERE personal_code = ?";
        Object[] args = new Object[]{personalCode};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ?";
        Object[] args = new Object[]{name, surname};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personalCode) {
        String sql = "SELECT * FROM patients WHERE name = ? AND personal_code = ?";
        Object[] args = new Object[]{name, personalCode};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personalCode) {
        String sql = "SELECT * FROM patients WHERE surname = ? AND personal_code = ?";
        Object[] args = new Object[]{surname, personalCode};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }

    @Override
    public List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personalCode) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ? AND personal_code = ?";
        Object[] args = new Object[]{name,surname, personalCode};
        return jdbcTemplate.query(sql, args, new PatientRawMapper());
    }
}
