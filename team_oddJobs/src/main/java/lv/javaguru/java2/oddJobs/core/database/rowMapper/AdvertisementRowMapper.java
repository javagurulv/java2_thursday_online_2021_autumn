package lv.javaguru.java2.oddJobs.core.database.rowMapper;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertisementRowMapper implements RowMapper<Advertisement> {

    @Override
    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {

        Advertisement advertisement = new Advertisement();
        advertisement.setAdvId(rs.getLong("advId"));
        advertisement.setAdvTitle(rs.getString("advTitle"));
        advertisement.setAdvDescription(rs.getString("advDescription"));
        return advertisement;
    }
}
