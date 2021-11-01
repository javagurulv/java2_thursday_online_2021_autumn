package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
public class JDBCVisitDatabaseImpl implements VisitDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void recordVisit(Visit visit) {
        jdbcTemplate.update(
                "INSERT INTO visits (doctor_id, patient_id, date, description) "
                        + "VALUES (?, ?, ?, ?)",
                visit.getDoctorID(), visit.getPatientID(), visit.getVisitDate(), visit.getDescription()
        );
    }

    @Override
    public boolean deleteVisit(Long id) {
        String sql = "DELETE FROM visits WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;

    }

    @Override
    public List<Visit> getAllVisits() {
        String sql = "SELECT * FROM visits";
        return jdbcTemplate.query(sql, new VisitRowMapper());
    }

    @Override
    public boolean editVisit(Long visitId, EditVisitEnum userInput, String changes) {
        String sql = "UPDATE visits SET " + userInput.toString().toLowerCase(Locale.ROOT)
                + " = ? WHERE id = ?";
        return jdbcTemplate.update(sql, changes, visitId) == 1;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        String sql = "SELECT * FROM visits WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        String sql = "SELECT * FROM visits WHERE doctor_id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        String sql = "SELECT * FROM visits WHERE patient_id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByDate(LocalDateTime date) {
        String sql = "SELECT * FROM visits WHERE date = ?";
        Object[] args = new Object[]{date};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        String sql = "SELECT * FROM visits WHERE doctor_id = ? AND patient_id = ?";
        Object[] args = new Object[]{doctorId, patientId};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        String sql = "SELECT * FROM visits WHERE doctor_id = ? AND date = ?";
        Object[] args = new Object[]{doctorId, date};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date) {
        String sql = "SELECT * FROM visits WHERE patient_id = ? AND date = ?";
        Object[] args = new Object[]{patientId, date};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date) {
        String sql = "SELECT * FROM visits WHERE doctor_id = ? AND patient_id = ? AND date = ?";
        Object[] args = new Object[]{doctorId, patientId, date};
        return jdbcTemplate.query(sql, args, new VisitRowMapper());
    }
}
