package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    void save(Prescription prescription);

    boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes);

    List<Prescription> findAll();

    boolean deleteById(Long id);

    List<Prescription> findById(Long prescriptionId);

    List<Prescription> findByDoctorId(Long doctorId);

    List<Prescription> findByPatientId(Long patientId);

    List<Prescription> findByDoctorIdAndPatientId(Long doctorId, Long patientId);

    List<Prescription> getById(Long id);
}
