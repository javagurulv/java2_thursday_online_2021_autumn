package lv.javaguru.java2.oddJobs.core.database;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AdvertisementRepository {


    @Autowired
    private SessionFactory sessionFactory;


    public void save(Advertisement advertisement) {
        sessionFactory.getCurrentSession().save(advertisement);
    }

    public Advertisement findById(Long id) {
        return sessionFactory.getCurrentSession().get(Advertisement.class, id);
    }
}
