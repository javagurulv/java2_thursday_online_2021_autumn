package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//@Component
public class JdbcUsersRepositoryImpl implements UsersRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void saveUserToRestaurantList(User userInfo) {
        jdbcTemplate.update(
                "INSERT INTO users (visitor_id, visitor_name, visitor_surname, visitor_telephone_number) "
                        + "VALUES (?, ?, ?, ?) ",
                userInfo.getUserId(),
                userInfo.getUserName(),
                userInfo.getSurname(),
                userInfo.getTelephoneNumber());
        String sql = "SELECT visitor_id FROM users WHERE visitor_name = ? AND visitor_surname = ? AND visitor_telephone_number = ?";
        Object[] args = new Object[]{userInfo.getUserName(), userInfo.getSurname(), userInfo.getTelephoneNumber()};
        Long renewedIdCodeUser = jdbcTemplate.queryForObject(sql, args, Long.class);
        userInfo.setUserId(renewedIdCodeUser);
    }

    @Override
    public List<User> findUsersByNameAndTelephoneNumber(String nameUsers, String telephoneNumber) {
        String sql = "SELECT * FROM users WHERE visitor_name = ? AND visitor_telephone_number = ?";
        Object[] args = new Object[]{nameUsers, telephoneNumber};
        return jdbcTemplate.query(sql, args, new UserRowMapper());
    }

    @Override
    public List<User> findUserById(Long userId) {
        String sql = "SELECT * FROM users WHERE visitor_id = ?";
        Object[] args = new Object[]{userId};
        return jdbcTemplate.query(sql, args, new UserRowMapper());
    }

    @Override
    public List<User> findByNameUser(String userName) {
        String sql = "SELECT * FROM users WHERE visitor_name = ?";
        Object[] args = new Object[]{userName};
        return jdbcTemplate.query(sql, args, new UserRowMapper());
    }

    @Override
    public List<User> findBySurnameUser(String userSurname) {
        String sql = "SELECT * FROM users WHERE visitor_surname = ?";
        Object[] args = new Object[]{userSurname};
        return jdbcTemplate.query(sql, args, new UserRowMapper());
    }

    @Override
    public List<User> findByNameAndSurname(String userName, String userSurname) {
        String sql = "SELECT * FROM users WHERE visitor_name = ? AND visitor_surname = ?";
        Object[] args = new Object[]{userName, userSurname};
        return jdbcTemplate.query(sql, args, new UserRowMapper());
    }

    @Override
    public boolean deleteUserWithIDAndName(Long id, String userName) {
        String sql = "DELETE FROM users WHERE visitor_id = ? AND visitor_name = ? ";
        Object[] args = new Object[]{id, userName};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<User> showAllUsersInList() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}
