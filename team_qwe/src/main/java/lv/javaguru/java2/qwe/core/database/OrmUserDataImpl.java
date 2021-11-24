package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.API.API;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@Transactional
public class OrmUserDataImpl implements UserData {

    @Autowired private SessionFactory sessionFactory;
    @Autowired private UtilityMethods utils;
    @Autowired private API api;
    @Autowired private Database database;

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
                .createQuery("FROM User u", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findUserByIdOrName(String userIdOrName) {
        try {
            Query<?> query = sessionFactory.getCurrentSession().createQuery(
                    "FROM User u WHERE id = :id OR name = :name");
            query.setParameter("id", getIdOrDefault(userIdOrName));
            query.setParameter("name", userIdOrName);
            return Optional.ofNullable((User) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Position> getUserPortfolio(Long userId) {
        User user =  sessionFactory.getCurrentSession()
                .find(User.class, userId);
        Hibernate.initialize(user.getPortfolio());
        api.getQuotesForMultipleSecurities(user.getPortfolio());
        return user.getPortfolio();
    }

    @Override
    public List<TradeTicket> getUserTrades(Long userId) {
        User user =  sessionFactory.getCurrentSession()
                .find(User.class, userId);
        Hibernate.initialize(user.getTrades());
        return user.getTrades();
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
    public void savePosition(Position position, User user) {
        position.setUser(user);
        Optional<Position> oldPosition = getOldPosition(position.getSecurity(), user);
        if (oldPosition.isPresent()) {
            replacePosition(position, oldPosition.get());
        }
        else {
            user.getPortfolio().add(position);
        }
    }

    @Override
    public void saveTradeTicket(TradeTicket ticket, User user) {
        user.getTrades().add(ticket);
    }

    private void replacePosition(Position position, Position oldPosition) {
        Position newPosition = mergePositions(oldPosition, position);
        Session cs = getSession();
        if (newPosition != null && position.getAmount() > 0) {
            position.getUser().getPortfolio().add(newPosition);
            position.getUser().getPortfolio().remove(oldPosition);
        }
        else if (newPosition != null && position.getAmount() < 0) {
            double oldPurchasePrice = oldPosition.getPurchasePrice();
            newPosition.setPurchasePrice(position.getPurchasePrice());
            oldPosition.setPurchasePrice(position.getPurchasePrice());
            cs.flush();
            position.getUser().getPortfolio().remove(oldPosition);
            position.getUser().getPortfolio().add(newPosition);
            cs.flush();
            newPosition.setPurchasePrice(oldPurchasePrice);
        }
        else {
            oldPosition.setPurchasePrice(position.getPurchasePrice());
            cs.flush();
            position.getUser().getPortfolio().remove(oldPosition);
        }
    }

    private Long getIdOrDefault(String userIdOrName) {
        try {
            return Long.parseLong(userIdOrName);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    private Optional<Position> getOldPosition(Security security, User user) {
        try {
            return Optional.of((Position) sessionFactory.getCurrentSession().createQuery(
                    "FROM Position p WHERE user_id = " + user.getId() +
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
            mergedPosition.setUser(oldPosition.getUser());
            return mergedPosition;
        }
        else if (newPosition.getAmount() < 0 && oldPosition.getAmount() + newPosition.getAmount() == 0) { // если после продажи позиция полностью закрывается
            return null;
        }
        else { // частичная продажа
            double totalQuantity = oldPosition.getAmount() + newPosition.getAmount();
            Position mergedPosition = new Position(newPosition.getSecurity(), totalQuantity, oldPosition.getPurchasePrice());
            mergedPosition.setUser(oldPosition.getUser());
            return mergedPosition;
        }
    }

    private void deleteOldPosition(Position oldPosition) {
        oldPosition.getUser().getPortfolio().remove(oldPosition);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void updateQuotesForPortfolio(List<Position> portfolio) {
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .mapToObj(i -> portfolio.get(i).getSecurity())
                .parallel()
                .forEach(security -> security.setMarketPrice(api.getQuote(security.getTicker())));
    }

}