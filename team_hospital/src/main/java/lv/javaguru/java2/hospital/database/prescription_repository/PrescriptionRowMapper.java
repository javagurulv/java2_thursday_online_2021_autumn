package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component
public class PrescriptionRowMapper implements RowMapper<Prescription> {

    @Autowired private DoctorRepository doctorRepository;
    @Autowired private PatientRepository patientRepository;

    @Override
    public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(rs.getLong("id"));
        prescription.setDoctor(doctorRepository.getById(rs.getLong("doctor_id")).get(0));
        prescription.setPatient(patientRepository.findById(rs.getLong("patient_id")).get());
        prescription.setMedicationName(rs.getString("medication_name"));
        prescription.setQuantity(rs.getInt("quantity"));
        prescription.setDate(rs.getDate("date").toLocalDate());
        prescription.setValidTill(rs.getDate("valid_till").toLocalDate());
        return prescription;
    }
}
