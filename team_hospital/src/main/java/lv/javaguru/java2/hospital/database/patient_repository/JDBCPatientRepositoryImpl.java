package lv.javaguru.java2.hospital.database.patient_repository;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

//@Component
public class JDBCPatientRepositoryImpl implements PatientRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private PatientRowMapper patientRowMapper;

    @Override
    public void save(Patient patient) {
        jdbcTemplate.update(
                "INSERT INTO patients (name, surname, personal_code) "
                        + "VALUES (?, ?, ?)",
                patient.getName(), patient.getSurname(), patient.getPersonalCode()
        );
    }

    @Override
    public Optional<Patient> findById(Long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        Object[] args = new Object[]{id};
        List<Patient> p = jdbcTemplate.query(sql, args, patientRowMapper);
        return Optional.of(p.get(0));
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM patients";
        return jdbcTemplate.query(sql, patientRowMapper);
    }

    @Override
    public boolean editPatient(Long patientID, String userInput, String input) {
        String sql = "UPDATE patients SET " + userInput.toLowerCase(Locale.ROOT) + " = ? WHERE id = ?";
        return jdbcTemplate.update(sql, input, patientID) == 1;
    }

    @Override
    public List<Patient> findByName(String name) {
        String sql = "SELECT * FROM patients WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findBySurname(String surname) {
        String sql = "SELECT * FROM patients WHERE surname = ?";
        Object[] args = new Object[]{surname};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findByPersonalCode(String personal_code) {
        String sql = "SELECT * FROM patients WHERE personal_code = ?";
        Object[] args = new Object[]{personal_code};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findByNameAndSurname(String name, String surname) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ?";
        Object[] args = new Object[]{name, surname};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findByNameAndPersonalCode(String name, String personal_code) {
        String sql = "SELECT * FROM patients WHERE name = ? AND personal_code = ?";
        Object[] args = new Object[]{name, personal_code};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findBySurnameAndPersonalCode(String surname, String personal_code) {
        String sql = "SELECT * FROM patients WHERE surname = ? AND personal_code = ?";
        Object[] args = new Object[]{surname, personal_code};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }

    @Override
    public List<Patient> findByNameAndSurnameAndPersonalCode(String name, String surname, String personal_code) {
        String sql = "SELECT * FROM patients WHERE name = ? AND surname = ? AND personal_code = ?";
        Object[] args = new Object[]{name, surname, personal_code};
        return jdbcTemplate.query(sql, args, patientRowMapper);
    }
}