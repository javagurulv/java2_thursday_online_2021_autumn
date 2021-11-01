package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

import static lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorEnum.NAME;

@Component
public class JdbcDoctorDatabaseImpl implements DoctorDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addDoctor(Doctor doctor) {
        jdbcTemplate.update(
                "INSERT INTO doctors (name, surname, speciality) " +
                        "VALUES (?, ?, ?)",
                doctor.getName(), doctor.getSurname(), doctor.getSpeciality());
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        String sql = "SELECT * FROM doctors";
        return jdbcTemplate.query(sql, new DoctorRowMapper());
    }

    @Override
    public boolean editDoctor(Long doctorId, Enum infoToEdit, String input) {
        String sql = "UPDATE doctors SET "
                + infoToEdit.toString().toLowerCase(Locale.ROOT)
                + " = ? WHERE id = ?";
        Object[] args = new Object[] {input, doctorId};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Doctor> findByName(String name) {
        String sql = "SELECT * FROM doctors WHERE name = ?";
        Object[] args = new Object[] {name};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findBySurname(String surname) {
        String sql = "SELECT * FROM doctors WHERE surname = ?";
        Object[] args = new Object[] {surname};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findByNameAndSurname(String name, String surname) {
        String sql = "SELECT * FROM doctors WHERE name = ? AND surname = ?";
        Object[] args = new Object[] {name, surname};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findById(Long id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        String sql = "SELECT * FROM doctors WHERE speciality = ?";
        Object[] args = new Object[] {speciality};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findByNameAndSpeciality(String name, String speciality) {
        String sql = "SELECT * FROM doctors WHERE name = ? AND speciality = ?";
        Object[] args = new Object[] {name, speciality};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findBySurnameAndSpeciality(String surname, String speciality) {
        String sql = "SELECT * FROM doctors WHERE surname = ? AND speciality = ?";
        Object[] args = new Object[] {surname, speciality};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }

    @Override
    public List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        String sql = "SELECT * FROM doctors WHERE name = ? AND surname = ? AND speciality = ?";
        Object[] args = new Object[] {name, surname, speciality};
        return jdbcTemplate.query(sql, args, new DoctorRowMapper());
    }
}
