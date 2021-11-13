package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorRowMapper implements RowMapper<Visitors> {

    @Override
    public Visitors mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visitors visitors = new Visitors();
        visitors.setIdClient(rs.getLong("visitor_id"));
        visitors.setClientName(rs.getString("visitor_name"));
        visitors.setSurname(rs.getString("visitor_surname"));
        visitors.setTelephoneNumber(rs.getString("visitor_telephone_number"));
        return visitors;
    }
}
