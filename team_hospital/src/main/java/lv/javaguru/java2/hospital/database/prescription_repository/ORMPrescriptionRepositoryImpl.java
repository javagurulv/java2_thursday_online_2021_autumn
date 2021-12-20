package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

//@Component
public class ORMPrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Prescription prescription) {
        sessionFactory.getCurrentSession().save(prescription);
    }

    @Override
    public boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Prescription p SET "
                        + prescriptionEnum.toString().toLowerCase(Locale.ROOT)
                        + " = :changes WHERE id = :id");
        query.setParameter("id", prescriptionID);
        if(prescriptionEnum.toString().toLowerCase(Locale.ROOT).equals("patient")) {
            query.setParameter("changes", Long.parseLong(changes));
        } else if(prescriptionEnum.toString().toLowerCase(Locale.ROOT).equals("medication_name")) {
            query.setParameter("changes", changes);
        } else {
            query.setParameter("changes", Integer.parseInt(changes));
        }
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Prescription> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Prescription p", Prescription.class)
                .getResultList();
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Prescription where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Prescription> findById(Long prescriptionId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Prescription p WHERE id = :id");
        query.setParameter("id", prescriptionId);
        return query.getResultList();
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Prescription p WHERE doctor_id = :doctor_id");
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Prescription p WHERE patient_id = :patient_id");
        query.setParameter("patient_id", patientId);
        return query.getResultList();
    }

    @Override
    public List<Prescription> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Prescription p WHERE doctor_id = :doctor_id AND " +
                        "patient_id = :patient_id");
        query.setParameter("doctor_id", doctorId);
        query.setParameter("patient_id", patientId);
        return query.getResultList();
    }

    @Override
    public List<Prescription> getById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Prescription p WHERE id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
