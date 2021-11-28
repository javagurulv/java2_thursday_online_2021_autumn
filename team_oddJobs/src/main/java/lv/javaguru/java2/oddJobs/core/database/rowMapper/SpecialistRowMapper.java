package lv.javaguru.java2.oddJobs.core.database.rowMapper;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialistRowMapper implements RowMapper<Specialist> {

    @Override
    public Specialist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Specialist specialist = new Specialist();
        specialist.setSpecialistId(rs.getLong("specialistId"));
        specialist.setSpecialistName(rs.getString("specialistName"));
        specialist.setSpecialistSurname(rs.getString("specialistSurname"));
        specialist.setSpecialistProfession("specialistProfession");
        return specialist;
    }
}
