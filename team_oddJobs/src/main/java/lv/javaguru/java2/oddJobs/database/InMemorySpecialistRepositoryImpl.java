package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.domain.Specialist;

import java.util.List;

public class InMemorySpecialistRepositoryImpl implements SpecialistRepository {
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
}
