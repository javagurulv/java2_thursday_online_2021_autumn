package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitRowMapper implements RowMapper<Visit> {

    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setVisitID(rs.getLong("id"));
        visit.setDoctorID(rs.getLong("doctor_id"));
        visit.setPatientID(rs.getLong("patient_id"));
        visit.setVisitDate(rs.getTimestamp("date").toLocalDateTime());
        //visit.setSqlDate(rs.getTimestamp("date").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        visit.setDescription(rs.getString("description"));
        return visit;
    }
}
