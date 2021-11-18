package lv.javaguru.java2.hospital.database.visit_repository;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class ORMVisitRepositoryImpl implements VisitRepository {

    @Autowired private SessionFactory sessionFactory;
    @Autowired private GetVisitDate getVisitDate;

    @Override
    public void recordVisit(Visit visit) {
        sessionFactory.getCurrentSession().save(visit);
    }

    @Override
    public boolean deleteVisit(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Visit where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Visit> getAllVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visit v", Visit.class)
                .getResultList();
    }

    @Override
    public boolean editVisit(Long visitId, EditVisitEnum userInput, String changes) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Visit SET "
                        + userInput.toString().toLowerCase(Locale.ROOT)
                        + " = :changes WHERE id = :id");
        query.setParameter("id", visitId);
        query.setParameter("changes", changes);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE doctor_id = :doctor_id");
        query.setParameter("doctor_id", id);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE patient_id = :patient_id");
        query.setParameter("patient_id", id);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByDate(LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE date = '" +
                        getVisitDate.getVisitStringFromDate(date) +"'");
        return query.getResultList();
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE doctor_id = :doctor_id AND " +
                        "patient_id = :patient_id");
        query.setParameter("doctor_id", doctorId);
        query.setParameter("patient_id", patientId);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE doctor_id = :doctor_id AND " +
                        "date = '" +
                        getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE patient_id = :patient_id AND " +
                        "date = '" + getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("patient_id", patientId);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIDAndDate(Long visitID, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "date = '" + getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("id", visitID);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE doctor_id = :doctor_id AND " +
                        "patient_id = :patient_id AND " +
                        "date = '" + getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("doctor_id", doctorId);
        query.setParameter("patient_id", patientId);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIdAndPatientId(Long visitID, Long patientID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "patient_id = :patient_id");
        query.setParameter("id", visitID);
        query.setParameter("patient_id", patientID);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIdAndDoctorId(Long visitID, Long doctorID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "doctor_id = :doctor_id");
        query.setParameter("id", visitID);
        query.setParameter("doctor_id", doctorID);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIdDoctorIdDate(Long visitID, Long doctorID, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "doctor_id = :doctor_id" +
                        " AND date = '" + getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("id", visitID);
        query.setParameter("doctor_id", doctorID);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIDAndDoctorIDAndPatientID(Long visitID, Long doctorID, Long patientID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "doctor_id = :doctor_id AND " +
                        "patient_id = :patient_id");
        query.setParameter("id", visitID);
        query.setParameter("doctor_id", doctorID);
        query.setParameter("patient_id", patientID);
        return query.getResultList();
    }

    @Override
    public List<Visit> findByVisitIDDoctorIDPatientIDDate(Long visitID, Long doctorID, Long patientID, LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE id = :id AND " +
                        "doctor_id = :doctor_id AND " +
                        "patient_id = :patient_id AND " +
                        "date = '" + getVisitDate.getVisitStringFromDate(date) +"'");
        query.setParameter("id", visitID);
        query.setParameter("doctor_id", doctorID);
        query.setParameter("patient_id", patientID);
        return query.getResultList();
    }
}
