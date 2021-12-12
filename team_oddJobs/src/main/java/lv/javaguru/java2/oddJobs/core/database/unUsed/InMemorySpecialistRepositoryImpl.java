package lv.javaguru.java2.oddJobs.core.database.unUsed;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InMemorySpecialistRepositoryImpl implements SpecialistRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addSpecialist(Specialist specialist) {

    }

    @Override
    public boolean removeSpecialistById(Long specialistId) {
        return false;
    }

    @Override
    public boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname) {
        return false;
    }

    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String specialistProfession) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        return null;
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        return null;
    }

    @Override
    public Optional<Specialist> getById(Long id) {
        return Optional.empty();
    }


}
