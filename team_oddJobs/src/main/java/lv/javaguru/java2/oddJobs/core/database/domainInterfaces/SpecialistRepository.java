package lv.javaguru.java2.oddJobs.core.database.domainInterfaces;

import lv.javaguru.java2.oddJobs.core.domain.Specialist;

import java.util.List;

public interface SpecialistRepository {

    void addSpecialist(Specialist specialist);

    boolean removeSpecialistById(Long specialistId);

    boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname);

    List<Specialist> findSpecialistById(Long specialistId);

    List<Specialist> findSpecialistByName(String specialistName);

    List<Specialist> findSpecialistBySurname(String specialistSurname);

    List<Specialist> findSpecialistByProfession(String specialistProfession);

    List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession);

    List<Specialist> getAllSpecialist();
}
