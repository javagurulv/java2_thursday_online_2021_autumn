package lv.javaguru.java2.oddJobs.database.rowMapper;

import lv.javaguru.java2.oddJobs.domain.Advertisement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertisementRowMapper implements RowMapper<Advertisement> {

    @Override
    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {

        Advertisement advertisement = new Advertisement();
        advertisement.setAdvId(rs.getLong("id"));
        advertisement.setAdvTitle(rs.getString("advTitle"));
        advertisement.setAdvDescription(rs.getString("advDescription"));
        return advertisement;
    }
}
