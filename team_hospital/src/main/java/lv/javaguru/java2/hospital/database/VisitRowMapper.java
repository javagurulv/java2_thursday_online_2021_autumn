package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

@Component
public class VisitRowMapper implements RowMapper<Visit> {

    @Autowired private PatientDatabase patientDatabase;
    @Autowired private DoctorDatabase doctorDatabase;

    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setVisitID(rs.getLong("id"));
        visit.setDoctor(doctorDatabase.findById(rs.getLong("doctor_id")).get(0));
        visit.setPatient(patientDatabase.findById(rs.getLong("patient_id")).get(0));
        visit.setVisitDate(rs.getTimestamp("date").toLocalDateTime());
        visit.setSqlDate(rs.getTimestamp("date").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        visit.setDescription(rs.getString("description"));
        return visit;
    }
}