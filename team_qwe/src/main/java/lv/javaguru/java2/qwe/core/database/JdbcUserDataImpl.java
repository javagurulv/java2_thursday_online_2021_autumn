package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcUserDataImpl implements UserData{

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private Database database;
    @Autowired private UtilityMethods utils;

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public Optional<Long> addUser(User user) {
        jdbcTemplate.update("INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?)",
                user.getName(), user.getAge(), String.valueOf(user.getType()), user.getInitialInvestment(),
                user.getInitialInvestment(), user.getPortfolioGenerationDate(), user.getRiskTolerance());
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
    }

    @Override
    public boolean removeUser(String idOrName) {
        return (utils.isLong(idOrName)) ? jdbcTemplate.update("DELETE FROM users\n" +
                "  WHERE id = ?", Long.parseLong(idOrName)) == 1
                : jdbcTemplate.update("DELETE FROM users\n" +
                "  WHERE name = ?", idOrName) == 1;
    }

    @Override
    public List<User> getAllUserList() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper(this));
    }

    @Override
    public Optional<User> findUserByIdOrName(String userIdOrName) {
        String sql = "SELECT * FROM users\n" +
                "  WHERE id = ? OR name = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(this), getIdOrDefault(userIdOrName), userIdOrName));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Position> getUserPortfolio(Long userId) {
        return jdbcTemplate.query("SELECT * FROM users_positions\n" +
                "  WHERE user_id = ?", new PositionRowMapper(database), userId);
    }

    @Override
    public Optional<Double> getUserCash(Long userID) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT cash FROM users\n" +
                "  WHERE id = ?", Double.class, userID));
    }

    @Override
    public void savePosition(Position position, Long userId) {
        jdbcTemplate.update("INSERT INTO users_positions (user_id, security_ticker, amount, purchase_price) VALUES\n" +
                "(?, ?, ?, ?)", userId, position.getSecurity().getTicker(), position.getAmount(), position.getPurchasePrice());
    }

    @Override
    public Map<String, List<String>> getUserPortfolioGroupedByIndustry(User user) {
        return null;
    }

    @Override
    public Map<String, Double> getUserInvestmentsByEachIndustry(User user) {
        return null;
    }

    private Long getIdOrDefault(String userIdOrName) {
        try {
            return Long.parseLong(userIdOrName);
        }
        catch (NumberFormatException e) {
            return -1L;
        }
    }

}