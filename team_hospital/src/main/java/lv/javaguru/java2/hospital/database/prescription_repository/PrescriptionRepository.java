package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;

import java.util.List;

public interface PrescriptionRepository {

    void addPrescription(Prescription prescription);

    boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes);

    List<Prescription> getAllPrescriptions();

    boolean deletePrescriptionById(Long id);

    List<Prescription> findByPrescriptionId(Long prescriptionId);

    List<Prescription> findByDoctorId(Long doctorId);

    List<Prescription> findByPatientId(Long patientId);

    List<Prescription> findByDoctorAndPatientId(Long doctorId, Long patientId);

    List<Prescription> findPatientForDeleting(Long patientID);
}
