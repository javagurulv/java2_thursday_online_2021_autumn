package lv.javaguru.java2.hospital.database.visit_repository;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component
public class VisitRowMapper implements RowMapper<Visit> {

    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setVisitID(rs.getLong("id"));
        visit.setDoctor(doctorRepository.findById(rs.getLong("doctor_id")).get(0));
        visit.setPatient(patientRepository.findById(rs.getLong("patient_id")).get());
        visit.setVisitDate(rs.getTimestamp("date").toLocalDateTime());
        visit.setDescription(rs.getString("description"));
        return visit;
    }
}