package lv.javaguru.java2.oddJobs.core.database;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmSpecialistRepositoryImpl implements SpecialistRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addSpecialist(Specialist specialist) {
        sessionFactory.getCurrentSession().save(specialist);
    }

    @Override
    public boolean removeSpecialistById(Long specialistId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Specialist where specialistId = :specialistId");
        query.setParameter("specialistId", specialistId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Specialist where specialistId = :specialistId AND specialistName = :specialistName AND specialistSurname = :specialistSurname");
        query.setParameter("specialistId", specialistId);
        query.setParameter("specialistName", specialistName);
        query.setParameter("specialistSurname", specialistSurname);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistId = :specialistId");
        query.setParameter("specialistId", specialistId);
        return query.getResultList();
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistName = :specialistName");
        query.setParameter("specialistName", specialistName);
        return query.getResultList();
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistSurname = :specialistSurname");
        query.setParameter("specialistSurname", specialistSurname);
        return query.getResultList();
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String specialistProfession) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistProfession = :specialistProfession");
        query.setParameter("specialistProfession", specialistProfession);
        return query.getResultList();
    }
    @Override
    public List<Specialist> findSpecialistByNameAndSurname(String specialistName, String specialistSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistName = :specialistName AND specialistSurname = :specialistSurname");
        query.setParameter("specialistName", specialistName);
        query.setParameter("specialistSurname", specialistSurname);
        return query.getResultList();
    }
    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Specialist b where specialistName = :specialistName AND specialistSurname = :specialistSurname AND specialistProfession = :specialistProfession ");
        query.setParameter("specialistName", specialistName);
        query.setParameter("specialistSurname", specialistSurname);
        query.setParameter("specialistProfession", specialistProfession);
        return query.getResultList();
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM  Specialist b ", Specialist.class)
                .getResultList();
    }

    @Override
    public Optional<Specialist> getById(Long id) {
        Specialist specialist = sessionFactory.getCurrentSession().get(Specialist.class, id);
        if (specialist == null) {
            return Optional.empty();
        } else {
            return Optional.of(specialist);
        }
    }
}

