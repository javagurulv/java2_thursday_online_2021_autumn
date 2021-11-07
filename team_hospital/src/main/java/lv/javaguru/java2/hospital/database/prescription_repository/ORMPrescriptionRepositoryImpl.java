package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ORMPrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void addPrescription(Prescription prescription) {
        sessionFactory.getCurrentSession().save(prescription);
    }

    @Override
    public boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes) {
        return false;
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Prescription p", Prescription.class)
                .getResultList();
    }

    @Override
    public boolean deletePrescriptionById(Long id) {
        return false;
    }

    @Override
    public List<Prescription> findByPrescriptionId(Long prescriptionId) {
        return null;
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        return null;
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        return null;
    }

    @Override
    public List<Prescription> findByDoctorAndPatientId(Long doctorId, Long patientId) {
        return null;
    }
}
