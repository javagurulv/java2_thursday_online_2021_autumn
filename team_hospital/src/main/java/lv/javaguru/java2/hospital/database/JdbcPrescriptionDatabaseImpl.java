package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class JdbcPrescriptionDatabaseImpl implements PrescriptionDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired private PrescriptionRowMapper rowMapper;

    @Override
    public void addPrescription(Prescription prescription) {
        jdbcTemplate.update(
                "INSERT INTO prescriptions (doctor_id, patient_id, medication_name, quantity, date, valid_till) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                prescription.getDoctor().getId(), prescription.getPatient().getId(), prescription.getMedication(),
                prescription.getQuantity(), prescription.getDate(), prescription.getValidTill());
    }

    @Override
    public boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes) {
        String sql = "UPDATE prescriptions SET "
                + prescriptionEnum.toString().toLowerCase(Locale.ROOT)
                + " = ? WHERE id = ?";
        Object[] args = new Object[]{changes, prescriptionID};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        String sql = "SELECT * FROM prescriptions";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean deletePrescriptionById(Long id) {
        String sql = "DELETE FROM prescriptions WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Prescription> findByPrescriptionId(Long prescriptionId) {
        String sql = "SELECT * FROM prescriptions WHERE id = ?";
        Object[] args = new Object[] {prescriptionId};
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        String sql = "SELECT * FROM prescriptions WHERE doctor_id = ?";
        Object[] args = new Object[] {doctorId};
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";
        Object[] args = new Object[] {patientId};
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    public List<Prescription> findByDoctorAndPatientId(Long doctorId, Long patientId) {
        String sql = "SELECT * FROM prescriptions WHERE doctor_id = ? AND patient_id = ?";
        Object[] args = new Object[] {doctorId, patientId};
        return jdbcTemplate.query(sql, args, rowMapper);
    }
}
