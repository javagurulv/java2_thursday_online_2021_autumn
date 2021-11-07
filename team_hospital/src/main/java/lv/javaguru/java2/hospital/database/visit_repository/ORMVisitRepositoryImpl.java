package lv.javaguru.java2.hospital.database.visit_repository;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class ORMVisitRepositoryImpl implements VisitRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void recordVisit(Visit visit) {
        sessionFactory.getCurrentSession().save(visit);
    }

    @Override
    public boolean deleteVisit(Long id) {
        return false;
    }

    @Override
    public List<Visit> getAllVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visit v", Visit.class)
                .getResultList();
    }

    @Override
    public boolean editVisit(Long visitId, EditVisitEnum userInput, String changes) {
        return false;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByVisitIdAndPatientId(Long visitID, Long patientID) {
        return null;
    }

    @Override
    public List<Visit> findByVisitIdAndDoctorId(Long visitID, Long doctorID) {
        return null;
    }

    @Override
    public List<Visit> findByVisitIDAndDoctorIDAndPatientID(Long visitID, Long doctorID, Long patientID) {
        return null;
    }

    @Override
    public List<Visit> findByVisitIDDoctorIDPatientIDDate(Long visitID, Long doctorID, Long patientID, LocalDateTime date) {
        return null;
    }
}
