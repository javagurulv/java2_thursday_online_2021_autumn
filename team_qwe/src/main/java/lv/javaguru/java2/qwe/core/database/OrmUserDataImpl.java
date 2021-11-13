package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class OrmUserDataImpl implements UserData {

    @Autowired private SessionFactory sessionFactory;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private Database database;
    @Autowired private UtilityMethods utils;

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public Long addUser(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean removeUser(String idOrName) {
        String sql = "DELETE User WHERE id = :id OR name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("id", getIdOrDefault(idOrName));
        query.setParameter("name", idOrName);
        return query.executeUpdate() == 1;
    }

    @Override
    public List<User> getAllUserList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findUserByIdOrName(String userIdOrName) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(
                    "SELECT u FROM User u WHERE id = :id OR name = :name");
            query.setParameter("id", getIdOrDefault(userIdOrName));
            query.setParameter("name", userIdOrName);
            return Optional.ofNullable((User) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Position> getUserPortfolio(Long userId) {
        return sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Position p WHERE user_id = " + userId, Position.class).getResultList();
    }

    @Override
    public Optional<Double> getUserCash(Long userID) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(
                    "SELECT u.cash FROM User u WHERE id = :id");
            query.setParameter("id", userID);
            return Optional.ofNullable((Double) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void savePosition(Position position, Long userId) {
        Optional<Position> oldPosition = getOldPosition(position.getSecurity(), userId);
        if (oldPosition.isPresent()) {
            Position newPosition = mergePositions(oldPosition.get(), position);
            jdbcTemplate.update("INSERT INTO users_positions (user_id, security_ticker, amount, purchase_price) VALUES\n" +
                            "(?, ?, ?, ?)",
                    userId, newPosition.getSecurity().getTicker(), newPosition.getAmount(), newPosition.getPurchasePrice());
        }
        else {
            jdbcTemplate.update("INSERT INTO users_positions (user_id, security_ticker, amount, purchase_price) VALUES\n" +
                            "(?, ?, ?, ?)",
                    userId, position.getSecurity().getTicker(), position.getAmount(), position.getPurchasePrice());
        }
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
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    private Optional<Position> getOldPosition(Security security, Long userId) {
        try {
            return Optional.of((Position) sessionFactory.getCurrentSession().createQuery(
                    "SELECT p FROM Position p WHERE user_id = " + userId +
                            " AND security_ticker = '" + security.getTicker() + "'"
            ).getSingleResult());
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    private Position mergePositions(Position oldPosition, Position newPosition) {
        if (newPosition.getAmount() > 0) { // если покупка
            double totalQuantity = oldPosition.getAmount() + newPosition.getAmount();
            double newPurchasePrice = utils.round(oldPosition.getPurchasePrice() * (oldPosition.getAmount() / totalQuantity) +
                    newPosition.getPurchasePrice() * (newPosition.getAmount() / totalQuantity));
            deleteOldPosition(oldPosition);
            return new Position(newPosition.getSecurity(), totalQuantity, newPurchasePrice);
        } else { // если продажа
            return null;
        }
    }

    private void deleteOldPosition(Position oldPosition) {
        sessionFactory.getCurrentSession().delete(oldPosition);
    }

}