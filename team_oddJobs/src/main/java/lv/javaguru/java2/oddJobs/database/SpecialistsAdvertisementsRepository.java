package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.domain.SpecialistsAdvertisements;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SpecialistsAdvertisementsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(SpecialistsAdvertisements specialistsAdvertisements) {
        sessionFactory.getCurrentSession().save(specialistsAdvertisements);
    }

    public SpecialistsAdvertisements getById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(SpecialistsAdvertisements.class, id);
    }
}
