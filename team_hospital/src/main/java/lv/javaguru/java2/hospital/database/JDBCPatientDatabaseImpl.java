package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class JDBCPatientDatabaseImpl implements PatientDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Patient patient) {
        jdbcTemplate.update(
                "INSERT INTO patients (name, surname, personal_code) "
                        + "VALUES (?, ?, ?)",
                patient.getName(), patient.getSurname(), patient.getPersonalCode()
        );
    }

    @Override
    public List<Patient> findById(Long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
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
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    @Override
    public boolean editActions(Long patientID, EditPatientEnum userInput, String input) {
        String sql = "UPDATE patients SET " + userInput.toString().toLowerCase(Locale.ROOT) + " = ? WHERE id = ?";
        return jdbcTemplate.update(sql, input, patientID) == 1;
    }

    @Override
    public List<Patient> findPatientsByName(String name) {
        String sql = "SELECT * FROM patients WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        String sql = "SELECT * FROM patients WHERE surname = ?";
        Object[] args = new Object[]{surname};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personal_code) {
        String sql = "SELECT * FROM patients WHERE personal_code = ?";
        Object[] args = new Object[]{personal_code};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ?";
        Object[] args = new Object[]{name, surname};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personal_code) {
        String sql = "SELECT * FROM patients WHERE name = ? AND personal_code = ?";
        Object[] args = new Object[]{name, personal_code};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personal_code) {
        String sql = "SELECT * FROM patients WHERE surname = ? AND personal_code = ?";
        Object[] args = new Object[]{surname, personal_code};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }

    @Override
    public List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personal_code) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ? AND personal_code = ?";
        Object[] args = new Object[]{name, surname, personal_code};
        return jdbcTemplate.query(sql, args, new PatientRowMapper());
    }
}
