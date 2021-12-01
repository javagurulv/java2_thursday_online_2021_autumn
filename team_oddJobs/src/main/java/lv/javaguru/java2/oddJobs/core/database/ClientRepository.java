package lv.javaguru.java2.oddJobs.core.database;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional

public class ClientRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }

    public Client findById(Long id) {
        return sessionFactory.getCurrentSession().get(Client.class, id);
    }
}
