package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmDatabaseImpl implements Database{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public String addStock(Stock stock) {
        return (String) sessionFactory.getCurrentSession().save(stock);
    }

    @Override
    public String addBond(Bond bond) {
        return (String) sessionFactory.getCurrentSession().save(bond);
    }

    @Override
    public boolean removeSecurity(String ticker) {
        String sql = (ticker.contains(" ")) ? "DELETE Stock WHERE ticker = :ticker" :
                "DELETE Bond WHERE ticker = :ticker";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("ticker", ticker);
        return query.executeUpdate() == 1;
    }

    @Override
    public List<Security> getAllSecurityList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM Stock s", Security.class)
                .getResultList();
    }

    @Override
    public Optional<Security> findSecurityByTickerOrName(String tickerOrName) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(
                    "select s FROM Stock s WHERE ticker = :ticker OR name = :name");
            query.setParameter("ticker", tickerOrName);
            query.setParameter("name", tickerOrName);
            return Optional.ofNullable((Security) query.getSingleResult());
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Security> filterStocksByMultipleParameters(String sql) {
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        return query.getResultList();
    }

}