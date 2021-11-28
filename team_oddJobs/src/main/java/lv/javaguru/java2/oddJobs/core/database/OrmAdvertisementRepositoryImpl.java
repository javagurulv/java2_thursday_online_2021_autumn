package lv.javaguru.java2.oddJobs.core.database;



import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Component
@Transactional
public class OrmAdvertisementRepositoryImpl implements AdvertisementRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAdvertisement(Advertisement advertisement) {
        sessionFactory.getCurrentSession().save(advertisement);
    }

    @Override
    public boolean removeAdvertisement(Long advId, String advTitle) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Advertisements where advId = :advId AND advTitle = :advTitle");
        query.setParameter("advID", advId);
        query.setParameter("advTitle", advTitle);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Advertisement> findAdvertisementByTitle(String advTitle) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Advertisements b where advTitle = :advTitle");
        query.setParameter("advTitle", advTitle);
        return query.getResultList();
    }

    @Override
    public List<Advertisement> findAdvertisementById(Long advId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Advertisements b where advId = :advId");
        query.setParameter("advId", advId);
        return query.getResultList();
    }

    @Override
    public List<Advertisement> getAllAdvertisement() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Advertisements b", Advertisement.class)
                .getResultList();
    }
}
