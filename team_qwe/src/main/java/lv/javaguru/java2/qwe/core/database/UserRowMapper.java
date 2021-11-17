package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private final UserData userData;

    public UserRowMapper(UserData userData) {
        this.userData = userData;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        User user = new User(
                rs.getString("name"),
                rs.getInt("age"),
                Type.valueOf(rs.getString("type")),
                rs.getDouble("initial_investment")
        );
        user.setId(id);
        user.setCash(userData.getUserCash(id).get());
        return user;
    }

}