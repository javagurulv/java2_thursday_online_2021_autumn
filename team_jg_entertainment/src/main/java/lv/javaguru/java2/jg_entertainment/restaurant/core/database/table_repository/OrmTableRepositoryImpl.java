package lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmTableRepositoryImpl implements TableRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Table table) {
        sessionFactory.getCurrentSession().save(table);
    }

    @Override
    public Optional<Table> getById(Long idTable) {
        Table table = sessionFactory.getCurrentSession().get(Table.class, idTable);
        if(table == null) {
            return Optional.empty();
        } else {
            return Optional.of(table);
        }
    }

    @Override
    public List<Table> findTableById(Long idTable) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Table t WHERE table_id = :table_id");
        query.setParameter("table_id", idTable);
        return query.getResultList();
    }

    @Override
    public List<Table> findByTitleTable(String titleTable) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT t FROM Table t WHERE table_title = :table_title");
        query.setParameter("table_title", titleTable);
        return query.getResultList();
    }

    @Override
    public List<Table> findByIdAndTitleTable(Long id, String titleTable) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Table t WHERE table_id = :table_id AND table_title = :table_title");
        query.setParameter("table_id", id);
        query.setParameter("table_title", titleTable);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Table WHERE table_id = :table_id");
        query.setParameter("table_id", id);
        int result = query.executeUpdate();
        return result ==1;
    }

    @Override
    public List<Table> getAllTables() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Table t", Table.class)
                .getResultList();
    }
}
