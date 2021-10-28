package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;

import java.util.List;

public interface PrescriptionDatabase {

    void addPrescription(Prescription prescription);

    boolean EditPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes);

    List<Prescription> getAllPrescriptions();

    boolean deletePrescriptionById(Long id);

    List<Prescription> findByPrescriptionId(Long prescriptionId);

    List<Prescription> findByDoctorId(Long doctorId);

    List<Prescription> findByPatientId(Long patientId);

    List<Prescription> findByDoctorAndPatientId(Long doctorId, Long patientId);
}
