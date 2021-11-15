package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
        Query<?> query = sessionFactory.getCurrentSession().createQuery(
                "DELETE User WHERE id = :id OR name = :name");
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
            Query<?> query = sessionFactory.getCurrentSession().createQuery(
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
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Position p WHERE user_id = " + userId, Position.class)
                .getResultList();
    }

    @Override
    public Optional<Double> getUserCash(Long userID) {
        try {
            Query<?> query = sessionFactory.getCurrentSession().createQuery(
                    "SELECT u.cash FROM User u WHERE id = " + userID);
            return Optional.ofNullable((Double) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void savePosition(Position position, Long userId) {
        position.setUserId(userId);
        Optional<Position> oldPosition = getOldPosition(position.getSecurity(), userId);
        if (oldPosition.isPresent()) {
            Position newPosition = mergePositions(oldPosition.get(), position);
            if (newPosition != null && position.getAmount() > 0) {
                sessionFactory.getCurrentSession().save(newPosition);
                sessionFactory.getCurrentSession().delete(oldPosition.get());
            }
            else if (newPosition != null && position.getAmount() < 0) {
                double oldPurchasePrice = oldPosition.get().getPurchasePrice();
                newPosition.setPurchasePrice(position.getPurchasePrice());
                oldPosition.get().setPurchasePrice(position.getPurchasePrice());
                sessionFactory.getCurrentSession().createQuery("FROM Position p WHERE user_id = " + userId).getResultList(); //???
                sessionFactory.getCurrentSession().delete(oldPosition.get());
                sessionFactory.getCurrentSession().save(newPosition);
                newPosition.setPurchasePrice(oldPurchasePrice);
            }
            else {
                oldPosition.get().setPurchasePrice(position.getPurchasePrice());
                sessionFactory.getCurrentSession().createQuery("FROM Position p WHERE user_id = " + userId).getResultList(); //???
                sessionFactory.getCurrentSession().delete(oldPosition.get());
            }
        }
        else {
            sessionFactory.getCurrentSession().save(position);
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
        if (newPosition.getAmount() > 0) { // если покупка, и позиция в этой акции уже есть
            double totalQuantity = oldPosition.getAmount() + newPosition.getAmount();
            double newPurchasePrice = utils.round(oldPosition.getPurchasePrice() * (oldPosition.getAmount() / totalQuantity) +
                    newPosition.getPurchasePrice() * (newPosition.getAmount() / totalQuantity));
            deleteOldPosition(oldPosition);
            Position mergedPosition = new Position(newPosition.getSecurity(), totalQuantity, newPurchasePrice);
            mergedPosition.setUserId(oldPosition.getUserId());
            return mergedPosition;
        }
        else if (newPosition.getAmount() < 0 && oldPosition.getAmount() + newPosition.getAmount() == 0) { // если после продажи позиция полностью закрывается
            return null;
        }
        else { // частичная продажа
            double totalQuantity = oldPosition.getAmount() + newPosition.getAmount();
            Position mergedPosition = new Position(newPosition.getSecurity(), totalQuantity, oldPosition.getPurchasePrice());
            mergedPosition.setUserId(oldPosition.getUserId());
            return mergedPosition;
        }
    }

    private void deleteOldPosition(Position oldPosition) {
        sessionFactory.getCurrentSession().delete(oldPosition);
    }

}