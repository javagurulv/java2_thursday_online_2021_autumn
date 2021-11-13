package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Transactional
public class OrmVisitorRepositoryImpl implements VisitorsRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void saveClientToRestaurantList(Visitors clientInfo) {
        sessionFactory.getCurrentSession().save(clientInfo);
    }

    @Override
    public List<Visitors> findVisitorsByNameAndTelephoneNumber(String nameVisitors, String telephoneNumber) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visitors v WHERE visitor_name = :visitor_name AND visitor_telephone_number = :visitor_telephone_number");
        query.setParameter("visitor_name", nameVisitors);
        query.setParameter("visitor_telephone_number", telephoneNumber);
        return query.getResultList();
    }

    @Override
    public List<Visitors> findClientById(Long idVisitors) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visitors v WHERE visitor_id = :visitor_id");
        query.setParameter("visitor_id", idVisitors);
        return query.getResultList();
    }

    @Override
    public List<Visitors> findByNameVisitor(String nameVisitor) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visitors v WHERE visitor_name = :visitor_name");
        query.setParameter("visitor_name", nameVisitor);
        return query.getResultList();
    }

    @Override
    public List<Visitors> findBySurnameVisitor(String surnameVisitor) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visitors v WHERE visitor_surname = :visitor_surname");
        query.setParameter("visitor_name", surnameVisitor);
        return query.getResultList();
    }

    @Override
    public List<Visitors> findByNameAndSurname(String nameVisitor, String surnameVisitor) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visitors v WHERE visitor_name = :visitor_name AND visitor_surname = :visitor_surname");
        query.setParameter("visitor_name", nameVisitor);
        query.setParameter("visitor_surname", surnameVisitor);
        return query.getResultList();
    }

    @Override
    public boolean deleteClientWithIDAndName(Long id, String nameVisitors) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Visitors WHERE visitor_id = :visitor_id AND visitor_name = :visitor_name");
        query.setParameter("visitor_id", id);
        query.setParameter("visitor_name", nameVisitors);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Visitors> showAllClientsInList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visitors v", Visitors.class)
                .getResultList();
    }
}
