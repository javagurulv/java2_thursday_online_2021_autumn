package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Transactional
public class OrmTableRepositoryImpl implements TableRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Table table) {
        sessionFactory.getCurrentSession().save(table);
    }

    @Override
    public List<Table> findTableById(Long idTable) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Tables t WHERE table_id = :table_id");
        query.setParameter("table_id", idTable);
        return query.getResultList();
    }

    @Override
    public List<Table> findByTitleTable(String titleTable) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT t FROM Tables t WHERE table_title = :table_title");
        query.setParameter("table_title", titleTable);
        return query.getResultList();
    }

    @Override
    public List<Table> findByIdAndTitleTable(Long id, String titleTable) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Tables t WHERE table_id = :table_id AND table_title = :table_title");
        query.setParameter("table_id", id);
        query.setParameter("table_title", titleTable);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Tables WHERE table_id = :table_id");
        query.setParameter("table_id", id);
        int result = query.executeUpdate();
        return result ==1;
    }

    @Override
    public List<Table> getAllTables() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Tables t", Table.class)
                .getResultList();
    }
}
