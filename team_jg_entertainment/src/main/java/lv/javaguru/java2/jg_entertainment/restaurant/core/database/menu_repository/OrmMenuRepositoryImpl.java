package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmMenuRepositoryImpl implements MenuRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    public Optional<Menu> getById(Long menuId) {
        Menu menu = sessionFactory.getCurrentSession().get(Menu.class, menuId);
        if(menu == null) {
            return Optional.empty();
        }else {
            return Optional.of(menu);
        }
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
    public List<Menu> findById(Long number) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Menu m WHERE menu_id = :menu_id");
        query.setParameter("menu_id", number);
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
        query.setParameter("menu_title", title);
        query.setParameter("menu_description", description);
        return query.getResultList();
    }
}
