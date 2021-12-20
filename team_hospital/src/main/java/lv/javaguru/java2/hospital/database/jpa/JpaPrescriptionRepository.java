package lv.javaguru.java2.hospital.database.jpa;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query(value = "UPDATE prescriptions SET ?2 = ?3 WHERE id = ?1", nativeQuery = true)
    boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes);

    @Query(value = "SELECT * FROM prescriptions WHERE doctor_id = ?1", nativeQuery = true)
    List<Prescription> findByDoctorId(Long doctorId);

    @Query(value = "SELECT * FROM prescriptions WHERE patient_id = ?1", nativeQuery = true)
    List<Prescription> findByPatientId(Long patientId);

    @Query(value = "SELECT * FROM prescriptions WHERE doctor_id = ?1 AND patient_id = ?2", nativeQuery = true)
    List<Prescription> findByDoctorIdAndPatientId(Long doctorId, Long patientId);

    @Query(value = "SELECT * FROM prescriptions WHERE id = ?1,", nativeQuery = true)
    List<Prescription> getById(Long id);
}
