package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PrescriptionRowMapper implements RowMapper<Prescription> {

    @Autowired private DoctorDatabase doctorDatabase;
    @Autowired private PatientDatabase patientDatabase;

    @Override
    public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(rs.getLong("id"));
        prescription.setDoctor(doctorDatabase.findById(rs.getLong("doctor_id")).get(0));
        prescription.setPatient(patientDatabase.findById(rs.getLong("patient_id")).get(0));
        prescription.setMedicationName(rs.getString("medication_name"));
        prescription.setQuantity(rs.getInt("quantity"));
        prescription.setDate(rs.getDate("date").toLocalDate());
        prescription.setValidTill(rs.getDate("valid_till").toLocalDate());
        return prescription;
    }
}
