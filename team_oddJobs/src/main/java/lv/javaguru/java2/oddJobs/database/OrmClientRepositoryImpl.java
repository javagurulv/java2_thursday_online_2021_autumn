package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.domain.Client;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OrmClientRepositoryImpl implements ClientRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addClient(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }


    @Override
    public boolean removeClientById(Long clientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Clients where clientId = :clientId");
        query.setParameter("clientId", clientId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean removeClient(Long clientId, String clientName, String clientSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Clients where clientId = :clientId AND clientName = :clientName AND clientSurname = :clientSurname");
        query.setParameter("clientId", clientId);
        query.setParameter("clientName", clientName);
        query.setParameter("clientSurname", clientSurname);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Client> findClientsById(Long clientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Clients b where clientId = :clientId");
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public List<Client> findClientsByName(String clientName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Clients b where clientName = :clientName");
        query.setParameter("clientName", clientName);
        return query.getResultList();
    }

    @Override
    public List<Client> findClientBySurname(String clientSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Clients b where clientSurname = :clientSurname");
        query.setParameter("clientSurname", clientSurname);
        return query.getResultList();
    }

    @Override
    public List<Client> findClientByIdAndNameAndSurname(Long clientId, String clientName, String clientSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Clients b where clientId = :clientId AND clientName = :clientName AND clientSurname = :clientSurname");
        query.setParameter("clientId", clientId);
        query.setParameter("clientName", clientName);
        query.setParameter("clientSurname", clientSurname);
        return query.getResultList();
    }

    @Override
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Clients b", Client.class)
                .getResultList();
    }
}
