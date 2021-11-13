package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Transactional
public class OrmMenuRepositoryImpl implements MenuRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    public boolean deleteByNr(Long number) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Menu WHERE menu_id = :menu_id");
        query.setParameter("menu_id", number);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Menu> getAllMenus() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m", Menu.class)
                .getResultList();
    }

    @Override
    public List<Menu> findById(Long idNumber) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m WHERE menu_id = :menu_id");
        query.setParameter("menu_id", idNumber);
        return query.getResultList();
    }

    @Override
    public List<Menu> findByTitle(String title) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m WHERE menu_title = :menu_title");
        query.setParameter("menu_title", title);
        return query.getResultList();
    }

    @Override
    public List<Menu> findByDescription(String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m WHERE menu_description = :menu_description");
        query.setParameter("menu_description", description);
        return query.getResultList();
    }

    @Override
    public List<Menu> findByTitleAndDescription(String title, String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m WHERE menu_title = :menu_title AND menu_description = :menu_description");
        return query.getResultList();
    }
}
