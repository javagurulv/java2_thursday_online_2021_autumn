package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("visitor_id"));
        user.setUserName(rs.getString("visitor_name"));
        user.setSurname(rs.getString("visitor_surname"));
        user.setTelephoneNumber(rs.getString("visitor_telephone_number"));
        return user;
    }
}
