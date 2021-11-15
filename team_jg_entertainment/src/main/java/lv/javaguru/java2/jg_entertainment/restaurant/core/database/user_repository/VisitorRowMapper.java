package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorRowMapper implements RowMapper<Visitor> {

    @Override
    public Visitor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setIdClient(rs.getLong("visitor_id"));
        visitor.setClientName(rs.getString("visitor_name"));
        visitor.setSurname(rs.getString("visitor_surname"));
        visitor.setTelephoneNumber(rs.getString("visitor_telephone_number"));
        return visitor;
    }
}
