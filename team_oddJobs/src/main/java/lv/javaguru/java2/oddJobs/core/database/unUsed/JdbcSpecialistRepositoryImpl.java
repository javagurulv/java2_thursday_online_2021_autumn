package lv.javaguru.java2.oddJobs.core.database.unUsed;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.database.rowMapper.SpecialistRowMapper;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class JdbcSpecialistRepositoryImpl implements SpecialistRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addSpecialist(Specialist specialist) {

        jdbcTemplate.update(
                "INSERT INTO Specialists (specialistName, specialistSurname,specialistProfession) "
                        + "VALUES (?, ?,?)",
                specialist.getSpecialistName(), specialist.getSpecialistSurname(), specialist.getSpecialistProfession()
        );
    }

    @Override
    public boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname) {
        String sql = "DELETE FROM specialists WHERE specialistId = ? AND specialistName = ? AND specialistSurname = ?";
        Object[] args = new Object[]{specialistId, specialistName, specialistSurname};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeSpecialistById(Long specialistId) {
        String sql = "DELETE FROM specialists WHERE specialistId = ?";
        Object[] args = new Object[]{specialistId};
        return jdbcTemplate.update(sql, args) == 1;
    }


    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        String sql = "SELECT * FROM specialists WHERE specialistId = ?";
        Object[] args = new Object[]{specialistId};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        String sql = "SELECT * FROM specialists WHERE specialistName = ?";
        Object[] args = new Object[]{specialistName};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        String sql = "SELECT * FROM specialists WHERE specialistSurname = ?";
        Object[] args = new Object[]{specialistSurname};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String specialistProfession) {
        String sql = "SELECT * FROM specialists WHERE profession = ?";
        Object[] args = new Object[]{specialistProfession};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurname(String specialistName, String specialistSurname) {
        String sql = "SELECT * FROM specialists WHERE specialistName = ? AND specialistSurname = ?";
        Object[] args = new Object[]{specialistName, specialistSurname};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        String sql = "SELECT * FROM specialists WHERE specialistName = ? AND specialistSurname = ? AND specialistProfession = ? ";
        Object[] args = new Object[]{specialistName, specialistSurname, specialistProfession};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        String sql = "SELECT * FROM specialists";
        return jdbcTemplate.query(sql, new SpecialistRowMapper());
    }

    @Override
    public Optional<Specialist> getById(Long id) {
        return Optional.empty();
    }
}
