package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getLong("id"));
        patient.setName(rs.getString("name"));
        patient.setSurname(rs.getString("surname"));
        patient.setPersonalCode(rs.getString("personal_code"));
        return patient;
    }
}
