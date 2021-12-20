package lv.javaguru.java2.hospital.database.jpa;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaVisitRepository extends JpaRepository<Visit, Long> {

    @Modifying
    @Query(value = "UPDATE Visit v SET doctor_id = :changes WHERE id = :id")
    void editVisitDoctor(@Param("id") Long id, @Param("changes") String input);

    @Modifying
    @Query(value = "UPDATE Visit v SET patient_id = :changes WHERE id = :id")
    void editVisitPatient(@Param("id") Long id, @Param("changes") String input);

    @Modifying
    @Query(value = "UPDATE Visit v SET date = :changes WHERE id = :id")
    void editVisitDate(@Param("id") Long id, @Param("changes") String input);

    @Modifying
    @Query(value = "UPDATE Visit v SET description = :changes WHERE id = :id")
    void editVisitDescription(@Param("id") Long id, @Param("changes") String input);

    @Query(value = "SELECT * FROM visits WHERE id = ?1", nativeQuery = true)
    List<Visit> getById(Long id);

    @Query(value = "SELECT * FROM visits WHERE doctor_id = ?1", nativeQuery = true)
    List<Visit> findByDoctorId(Long id);

    @Query(value = "SELECT * FROM visits WHERE patient_id = ?1", nativeQuery = true)
    List<Visit> findByPatientId(Long id);

    @Query(value = "SELECT * FROM visits WHERE date = ?1", nativeQuery = true)
    List<Visit> findByDate(LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE doctor_id = ?1 AND patient_id = ?2", nativeQuery = true)
    List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId);

    @Query(value = "SELECT * FROM visits WHERE doctor_id = ?1 AND date = ?2", nativeQuery = true)
    List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE patient_id = ?1 AND date = ?2", nativeQuery = true)
    List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND date = ?2", nativeQuery = true)
    List<Visit> findByVisitIDAndDate(Long visitID, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE doctor_id = ?1 AND patient_id = ?2 AND date = ?3", nativeQuery = true)
    List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND patient_id = ?2", nativeQuery = true)
    List<Visit> findByIdAndPatientId(Long visitID, Long patientID);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND doctor_id = ?2", nativeQuery = true)
    List<Visit> findByVisitIdAndDoctorId(Long visitId, Long doctorId);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND doctor_id = ?2 AND date = ?3", nativeQuery = true)
    List<Visit> findByVisitIdDoctorIdDate(Long visitId, Long doctorId, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND patient_id = ?2 AND date = ?3", nativeQuery = true)
    List<Visit> findByVisitIdPatientIdDate(Long visitId, Long patientId, LocalDateTime date);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND doctor_id = ?2 AND patient_id = ?3", nativeQuery = true)
    List<Visit> findByVisitIdAndDoctorIdAndPatientId(Long visitId, Long doctorId, Long patientId);

    @Query(value = "SELECT * FROM visits WHERE id = ?1 AND doctor_id = ?2 AND patient_id = ?3 AND date = ?4", nativeQuery = true)
    List<Visit> findByIdAndDoctorIdAndPatientIdAndDate(Long visitId, Long doctorId, Long patientId, LocalDateTime date);
}
