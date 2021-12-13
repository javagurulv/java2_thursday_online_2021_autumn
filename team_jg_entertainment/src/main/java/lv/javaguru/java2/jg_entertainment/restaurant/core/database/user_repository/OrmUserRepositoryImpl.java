package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UsersRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void saveUserToRestaurantList(User clientInfo) {
        sessionFactory.getCurrentSession().save(clientInfo);
    }

    @Override
    public Optional<User> getById(Long userId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        if(user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    @Override
    public List<User> findUsersByNameAndTelephoneNumber(String nameUsers, String telephoneNumber) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM User v WHERE visitor_name = :visitor_name AND visitor_telephone_number = :visitor_telephone_number");
        query.setParameter("visitor_name", nameUsers);
        query.setParameter("visitor_telephone_number", telephoneNumber);
        return query.getResultList();
    }

    @Override
    public List<User> findUserById(Long userId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM User v WHERE visitor_id = :visitor_id");
        query.setParameter("visitor_id", userId);
        return query.getResultList();
    }

    @Override
    public List<User> findByNameUser(String userName) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM User v WHERE visitor_name = :visitor_name");
        query.setParameter("visitor_name", userName);
        return query.getResultList();
    }

    @Override
    public List<User> findBySurnameUser(String userSurname) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM User v WHERE visitor_surname = :visitor_surname");
        query.setParameter("visitor_name", userSurname);
        return query.getResultList();
    }

    @Override
    public List<User> findByNameAndSurname(String userName, String userSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM User v WHERE visitor_name = :visitor_name AND visitor_surname = :visitor_surname");
        query.setParameter("visitor_name", userName);
        query.setParameter("visitor_surname", userSurname);
        return query.getResultList();
    }

    @Override
    public boolean deleteUserWithIDAndName(Long id, String userName) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE User WHERE visitor_id = :visitor_id AND visitor_name = :visitor_name");
        query.setParameter("visitor_id", id);
        query.setParameter("visitor_name", userName);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override//new path
    public boolean deleteUserWithID(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete User WHERE visitor_id = :visitor_id");
        query.setParameter("visitor_id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<User> showAllUsersInList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM User v", User.class)
                .getResultList();
    }
}
