package lv.javaguru.java2.oddJobs.core.database;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SpecialistRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public void save(Specialist specialist) {
        sessionFactory.getCurrentSession().save(specialist);
    }

    public Specialist findById(Long id) {
        return sessionFactory.getCurrentSession().get(Specialist.class, id);
    }

}
